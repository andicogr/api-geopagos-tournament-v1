package com.geopagos.tournament.business.steps;

import com.geopagos.tournament.model.domain.Match;
import com.geopagos.tournament.model.domain.TournamentLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class DefineTournamentScheduleStep implements TournamentStep {

    @Override
    public Integer getOrder() {
        return 20;
    }

    @Override
    public void execute(TournamentContext context) {

        log.info("Starting the definition of Tournament Schedule");

        int levels = (int) (Math.log(context.getPlayers().size()) / Math.log(2));

        log.info(String.format("The number of level is %s", levels));

        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        do {

            log.info(String.format("Creating Tournament Level %s", levels));

            TournamentLevel tournamentLevel = new TournamentLevel();
            tournamentLevel.setLevel(levels);

            Integer matchesPerLevel = (int) Math.pow(2, levels - 1.0);

            tournamentLevel.setMatches(createMatches(levels, matchesPerLevel));

            tournamentLevels.add(tournamentLevel);
            levels--;

        } while (levels >= 1);

        linkMatchesWithMatchesFromPreviousLevel(tournamentLevels);

        context.getTournament().setLevels(tournamentLevels);

        log.info("Ending the definition of Tournament Schedule");
    }

    private List<Match> createMatches(Integer level, Integer matches) {

        List<Match> matchList = new ArrayList<>();

        int matchCode = 1;
        for (int i = 0; i < matches; i++) {

            Match match = new Match();
            match.setCode("LEVEL" + level + "-" + StringUtils.leftPad(Integer.toString(matchCode), 5, "0"));

            log.info(String.format("Creating Match code %s", match.getCode()));

            matchList.add(match);

            matchCode++;
        }

        return matchList;
    }

    private void linkMatchesWithMatchesFromPreviousLevel(List<TournamentLevel> tournamentLevels) {

        int levelCount = 0;

        for (TournamentLevel tournamentLevel: tournamentLevels) {
            if (levelCount == 0) {
                levelCount++;
                continue;
            }

            int matchCount = 0;
            for (Match match: tournamentLevel.getMatches()) {
                TournamentLevel previousLevel = tournamentLevels.get(levelCount - 1);
                match.setPlayer1MatchCode(previousLevel.getMatches().get(matchCount).getCode());
                matchCount++;
                match.setPlayer2MatchCode(previousLevel.getMatches().get(matchCount).getCode());
                matchCount++;
            }

            levelCount++;

        }

    }

}
