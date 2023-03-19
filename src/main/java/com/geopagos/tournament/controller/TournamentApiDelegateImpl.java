package com.geopagos.tournament.controller;

import com.geopagos.tournament.business.TournamentService;
import com.geopagos.tournament.controller.mapper.TournamentMapper;
import com.geopagos.tournament.model.api.Gender;
import com.geopagos.tournament.model.api.TournamentResponse;
import com.geopagos.tournament.model.api.TournamentResultResponse;
import com.geopagos.tournament.model.api.TournamentStartRequest;
import com.geopagos.tournament.model.api.TournamentsResponse;
import com.geopagos.tournament.model.domain.Tournament;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TournamentApiDelegateImpl implements TournamentsApiDelegate {

    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;

    @Override
    public ResponseEntity<TournamentResponse> findTournament(String tournamentId) {
        return ResponseEntity.ok(tournamentMapper.toResponse(tournamentService.findById(tournamentId)));
    }

    @Override
    public ResponseEntity<List<TournamentsResponse>> getTournaments(String date, Gender gender) {

        return ResponseEntity.ok(
          tournamentService.findTournaments(tournamentMapper.fromSearchRequest(date, gender))
                .stream()
                .map(tournamentMapper::toTournamentsResponse)
                .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<TournamentResultResponse> startTournament(TournamentStartRequest tournamentStartRequest) {

        return ResponseEntity.ok(
                tournamentMapper.toResultResponse(tournamentService.start(tournamentMapper.configurationFromRequest(tournamentStartRequest)))
        );

    }
}
