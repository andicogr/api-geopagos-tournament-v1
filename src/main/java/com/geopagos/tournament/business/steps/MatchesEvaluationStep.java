package com.geopagos.tournament.business.steps;

import com.geopagos.tournament.config.TournamentConfig;
import com.geopagos.tournament.dao.LuckyDao;
import com.geopagos.tournament.model.domain.Match;
import com.geopagos.tournament.model.domain.TournamentAttributes;
import com.geopagos.tournament.model.domain.TournamentLevel;
import lombok.AllArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class MatchesEvaluationStep implements TournamentStep {

    private static final Integer FINAL_LEVEL = 1;
    private final TournamentConfig tournamentConfig;
    private final LuckyDao luckyDao;

    @Override
    public Integer getOrder() {
        return 40;
    }

    @Override
    public void execute(TournamentContext context) {

        List<TournamentAttributes> tournamentAttributes = getAttributesToEvaluate(context);

        context.getTournament().setAttributes(tournamentAttributes);
        context.getTournament().getLevels().sort(Comparator.comparingInt(TournamentLevel::getLevel).reversed());

        TournamentLevel previousLevel = null;
        for (TournamentLevel tournamentLevel: context.getTournament().getLevels()) {

            if (previousLevel != null) {
                setWinnersFromPreviousTournamentLevel(previousLevel, tournamentLevel);
            }

            tournamentLevel.getMatches().forEach(match ->  playMatch(match, tournamentAttributes));

            previousLevel = tournamentLevel;

            if (Objects.equals(tournamentLevel.getLevel(), FINAL_LEVEL)) {
                context.getTournament().setWinner(tournamentLevel.getMatches().get(0).getWinner());
            }

        }

    }

    private List<TournamentAttributes> getAttributesToEvaluate(TournamentContext context){

        List<TournamentAttributes> tournamentDefaultAttributes = tournamentConfig.getDefaultAdditionalAttributes().get(context.getTournament().getGender());

        if (CollectionUtils.isNotEmpty(context.getCustomTournamentAttributes())) {
            tournamentDefaultAttributes = context.getCustomTournamentAttributes();
        }

        return tournamentDefaultAttributes;
    }

    private void setWinnersFromPreviousTournamentLevel(TournamentLevel previousLevel, TournamentLevel currentLevel) {
        for (Match match: currentLevel.getMatches()) {
            match.setPlayer1(previousLevel.getWinnerByMatchCode(match.getPlayer1MatchCode()));
            match.setPlayer2(previousLevel.getWinnerByMatchCode(match.getPlayer2MatchCode()));
        }
    }

    public void playMatch(Match match, List<TournamentAttributes> attributes) {

        double p1Skill = match.getPlayer1().getAverageStatistics(attributes) * luckyDao.getLucky();
        double p2Skill = match.getPlayer2().getAverageStatistics(attributes) * luckyDao.getLucky();

        do {
            if (p1Skill > p2Skill) {
                match.setWinner(match.getPlayer1());
            } else if(p2Skill > p1Skill) {
                match.setWinner(match.getPlayer2());
            }
        } while (match.getWinner() == null);

    }

}
