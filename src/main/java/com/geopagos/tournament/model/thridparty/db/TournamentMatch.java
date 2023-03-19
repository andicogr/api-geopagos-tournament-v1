package com.geopagos.tournament.model.thridparty.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentMatch {

    private String code;
    private String player1MatchCode;
    private TournamentPlayer player1;
    private String player2MatchCode;
    private TournamentPlayer player2;
    private TournamentPlayer winner;

}
