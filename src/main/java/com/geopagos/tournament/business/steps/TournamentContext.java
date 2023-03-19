package com.geopagos.tournament.business.steps;

import com.geopagos.tournament.model.domain.Player;
import com.geopagos.tournament.model.domain.Tournament;
import com.geopagos.tournament.model.domain.TournamentAttributes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TournamentContext {

    private Tournament tournament;
    private List<String> participants;
    private List<Player> players;
    private List<TournamentAttributes> customTournamentAttributes;

}
