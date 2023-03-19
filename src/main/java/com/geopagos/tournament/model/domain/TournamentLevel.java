package com.geopagos.tournament.model.domain;

import com.geopagos.tournament.exceptions.MatchesEvaluationException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TournamentLevel {

    private Integer level;
    private List<Match> matches;

    public Player getWinnerByMatchCode(String matchCode) {

        return matches.stream()
                .filter(match -> match.getCode().equals(matchCode))
                .findFirst()
                .orElseThrow(() -> new MatchesEvaluationException(String.format("The Match code %s was not found in the Tournament level %s", matchCode, level)))
                .getWinner();
    }

}
