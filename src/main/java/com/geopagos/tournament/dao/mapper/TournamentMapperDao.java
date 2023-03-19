package com.geopagos.tournament.dao.mapper;

import com.geopagos.tournament.model.domain.Tournament;
import com.geopagos.tournament.model.thridparty.db.TournamentDocument;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TournamentMapperDao {

    TournamentDocument toDocument(Tournament tournament);

    Tournament fromDocument(TournamentDocument document);

}
