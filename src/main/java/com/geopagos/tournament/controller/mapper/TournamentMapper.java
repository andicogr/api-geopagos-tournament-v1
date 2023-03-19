package com.geopagos.tournament.controller.mapper;

import com.geopagos.tournament.model.api.Gender;
import com.geopagos.tournament.model.api.TournamentResponse;
import com.geopagos.tournament.model.api.TournamentResultResponse;
import com.geopagos.tournament.model.api.TournamentStartRequest;
import com.geopagos.tournament.model.api.TournamentsResponse;
import com.geopagos.tournament.model.domain.Tournament;
import com.geopagos.tournament.model.domain.TournamentConfiguration;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface TournamentMapper {

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Mapping(source = "extraAttributesToEval", target = "attributes")
    TournamentConfiguration configurationFromRequest(TournamentStartRequest request);

    TournamentResultResponse toResultResponse(Tournament tournament);

    TournamentsResponse toTournamentsResponse(Tournament tournament);

    TournamentResponse toResponse(Tournament tournament);

    com.geopagos.tournament.model.domain.Gender fromRequest(Gender gender);

    default Tournament fromSearchRequest(String date, Gender gender) {
        Tournament tournament = new Tournament();
        tournament.setGender(fromRequest(gender));
        if (date != null) {
            tournament.setDate(LocalDate.parse(date, FORMATTER));
        }
        return tournament;
    }

}
