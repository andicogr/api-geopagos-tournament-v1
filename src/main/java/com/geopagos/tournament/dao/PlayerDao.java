package com.geopagos.tournament.dao;

import com.geopagos.tournament.dao.mapper.PlayerMapperDao;
import com.geopagos.tournament.dao.repository.api.PlayerRepository;
import com.geopagos.tournament.exceptions.PlayerException;
import com.geopagos.tournament.model.domain.Player;
import com.geopagos.tournament.model.thridparty.api.PlayerResponse;
import com.geopagos.tournament.model.thridparty.api.PlayerSearchRequest;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PlayerDao {

    private final PlayerRepository playerRepository;
    private final PlayerMapperDao playerMapperDao;

    public List<Player> find(Player player) {

        ResponseEntity<List<PlayerResponse>> response = playerRepository.findPlayers(playerMapperDao.toSearchRequest(player));

        if (response.getStatusCode().isError()) {
            throw new PlayerException("Error getting players from repository");
        }

        return response
                .getBody()
                .stream()
                .map(playerMapperDao::fromResponse)
                .collect(Collectors.toList());
    }

    public List<Player> find(List<String> playerIds) {

        return playerIds.stream()
                .map(playerRepository::findPlayerById)
                .map(response -> {

                    if (response.getStatusCode().isError()) {
                        throw new PlayerException("Error getting player from repository");
                    }

                    return response.getBody();
                })
                .map(playerMapperDao::fromResponse)
                .collect(Collectors.toList());

    }

}
