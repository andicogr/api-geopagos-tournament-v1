package com.geopagos.tournament.business.steps;

import com.geopagos.tournament.exceptions.AssignPlayersToMatchesException;
import com.geopagos.tournament.model.domain.Match;
import com.geopagos.tournament.model.domain.TournamentLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@AllArgsConstructor
public class AssignPlayersToMatchesStep implements TournamentStep {

    @Override
    public Integer getOrder() {
        return 30;
    }

    @Override
    public void execute(TournamentContext context) {

        Collections.shuffle(context.getPlayers());

        TournamentLevel tournamentLevel = context.getTournament().getLevels()
                .stream()
                .filter(item -> item.getLevel() == context.getTournament().getLevels().size())
                .findFirst()
                .orElseThrow(() -> new AssignPlayersToMatchesException("The first tournament level can't be found"));

        int playerCount = 0;

        for (Match match: tournamentLevel.getMatches()) {

            match.setPlayer1(context.getPlayers().get(playerCount));
            playerCount++;
            match.setPlayer2(context.getPlayers().get(playerCount));
            playerCount++;

        }

    }

}
