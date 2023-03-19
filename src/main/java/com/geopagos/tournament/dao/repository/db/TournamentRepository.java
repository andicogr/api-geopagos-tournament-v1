package com.geopagos.tournament.dao.repository.db;

import com.geopagos.tournament.model.thridparty.db.TournamentDocument;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TournamentRepository extends MongoRepository<TournamentDocument, String> {

}
