package com.geopagos.tournament.model.thridparty.db;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TournamentLevel {

    private Integer level;
    private List<TournamentMatch> matches;

}
