package com.geopagos.tournament.business.steps;

import com.geopagos.tournament.dao.PlayerDao;
import com.geopagos.tournament.exceptions.InvalidParticipantsGenderException;
import com.geopagos.tournament.exceptions.InvalidTournamentParticipantException;
import com.geopagos.tournament.model.domain.Gender;
import com.geopagos.tournament.model.domain.Player;
import lombok.AllArgsConstructor;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ParticipantsValidationStep implements TournamentStep {

    private final PlayerDao playerDao;

    @Override
    public Integer getOrder() {
        return 10;
    }

    @Override
    public void execute(TournamentContext context) {

        context.setPlayers(getPlayers(context));
        context.getTournament().setParticipants(context.getPlayers().size());

        validateQuantity(context.getPlayers().size());

    }

    private void validateQuantity(Integer participantsQuantity) {

        if (participantsQuantity < 2 || ((Math.log(participantsQuantity) / Math.log(2)) % 1) != 0) {
            throw new InvalidTournamentParticipantException(participantsQuantity);
        }

    }

    private List<Player> getPlayers(TournamentContext context) {

        if (CollectionUtils.isEmpty(context.getParticipants())) {
            Player playerSearch = new Player();
            playerSearch.setGender(context.getTournament().getGender());

            return playerDao.find(playerSearch);

        } else {

            List<Player> players = playerDao.find(context.getParticipants().stream().distinct().collect(Collectors.toList()));

            if (players.stream().anyMatch(player -> context.getTournament().getGender() != player.getGender())) {
                throw new InvalidParticipantsGenderException();
            }

            return players;
        }

    }
}
