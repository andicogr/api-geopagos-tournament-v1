package com.geopagos.tournament.model.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TournamentConfiguration {

    Gender gender;
    List<TournamentAttributes> attributes;
    List<String> participants;

}
