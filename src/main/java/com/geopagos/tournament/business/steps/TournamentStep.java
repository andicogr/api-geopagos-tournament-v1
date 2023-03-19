package com.geopagos.tournament.business.steps;

import com.geopagos.tournament.model.domain.Tournament;

public interface TournamentStep {

    Integer getOrder();

    void execute(TournamentContext context);

}
