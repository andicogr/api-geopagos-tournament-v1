package com.geopagos.tournament.model.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {

    private String code;
    private String player1MatchCode;
    private Player player1;
    private String player2MatchCode;
    private Player player2;
    private Player winner;

}
