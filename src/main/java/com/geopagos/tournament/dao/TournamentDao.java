package com.geopagos.tournament.dao;

import com.geopagos.tournament.dao.mapper.TournamentMapperDao;
import com.geopagos.tournament.dao.repository.db.TournamentRepository;
import com.geopagos.tournament.exceptions.RecordNotFoundException;
import com.geopagos.tournament.model.domain.Tournament;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TournamentDao {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapperDao tournamentMapperDao;

    public Tournament findById(String tournamentId) {
        return tournamentMapperDao.fromDocument(tournamentRepository.findById(tournamentId).orElseThrow(RecordNotFoundException::new));
    }

    public Tournament save(Tournament tournament) {
        return tournamentMapperDao.fromDocument(tournamentRepository.save(tournamentMapperDao.toDocument(tournament)));
    }

    public List<Tournament> find(Tournament tournament) {

        ExampleMatcher exampleMatcher = ExampleMatcher.matchingAll()
                .withMatcher("gender", ExampleMatcher.GenericPropertyMatchers.exact().caseSensitive())
                .withMatcher("date", ExampleMatcher.GenericPropertyMatchers.exact().caseSensitive());

        return tournamentRepository.findAll(Example.of(tournamentMapperDao.toDocument(tournament), exampleMatcher))
                .stream()
                .map(tournamentMapperDao::fromDocument)
                .collect(Collectors.toList());
    }
}
