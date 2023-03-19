package com.geopagos.tournament.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class Tournament {

    private String id;
    private LocalDate date;
    private Gender gender;
    private List<TournamentAttributes> attributes;
    private Integer participants;
    private Player winner;
    private List<TournamentLevel> levels;

}
