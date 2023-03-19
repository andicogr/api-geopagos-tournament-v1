package com.geopagos.tournament.business;

import com.geopagos.tournament.business.steps.TournamentContext;
import com.geopagos.tournament.business.steps.TournamentStep;
import com.geopagos.tournament.dao.TournamentDao;
import com.geopagos.tournament.model.domain.Player;
import com.geopagos.tournament.model.domain.Tournament;
import com.geopagos.tournament.model.domain.TournamentConfiguration;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class TournamentService {

    private final List<TournamentStep> tournamentSteps;
    private final TournamentDao tournamentDao;

    public TournamentService(List<TournamentStep> tournamentSteps, TournamentDao tournamentDao) {
        this.tournamentSteps = tournamentSteps;
        this.tournamentDao = tournamentDao;

        this.tournamentSteps.sort(Comparator.comparingInt(TournamentStep::getOrder));
    }

    public Tournament findById(String tournamentId) {
        return tournamentDao.findById(tournamentId);
    }

    public Tournament start(TournamentConfiguration tournamentConfiguration) {

        Tournament tournament = new Tournament();
        tournament.setGender(tournamentConfiguration.getGender());
        tournament.setDate(LocalDate.now());

        TournamentContext context = new TournamentContext();
        context.setTournament(tournament);
        context.setParticipants(tournamentConfiguration.getParticipants());
        context.setCustomTournamentAttributes(tournamentConfiguration.getAttributes());

        tournamentSteps.forEach(tournamentStep -> tournamentStep.execute(context));

        return tournamentDao.save(tournament);
    }

    public List<Tournament> findTournaments(Tournament tournament) {
        return tournamentDao.find(tournament);
    }
}
