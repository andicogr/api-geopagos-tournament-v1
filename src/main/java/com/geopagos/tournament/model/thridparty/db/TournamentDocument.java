package com.geopagos.tournament.model.thridparty.db;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document("tournament")
public class TournamentDocument {

    @Id
    private String id;
    private String date;
    private String gender;
    private Integer participants;
    private List<String> attributes;
    private TournamentPlayer winner;
    private List<TournamentLevel> levels;

}
