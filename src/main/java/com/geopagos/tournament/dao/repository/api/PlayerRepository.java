package com.geopagos.tournament.dao.repository.api;

import com.geopagos.tournament.model.thridparty.api.PlayerResponse;
import com.geopagos.tournament.model.thridparty.api.PlayerSearchRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
@FeignClient(value = "playerRepository", url = "${application.http-client.api-player-v1.base-url}")
public interface PlayerRepository {

    @GetMapping
    ResponseEntity<List<PlayerResponse>> findPlayers(@SpringQueryMap PlayerSearchRequest playerSearchRequest);

    @GetMapping("/{playerId}")
    ResponseEntity<PlayerResponse> findPlayerById(@PathVariable("playerId") String playerId);

}
