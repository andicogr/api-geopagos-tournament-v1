package com.geopagos.tournament.dao.mapper;

import com.geopagos.tournament.model.domain.Player;
import com.geopagos.tournament.model.thridparty.api.PlayerResponse;
import com.geopagos.tournament.model.thridparty.api.PlayerSearchRequest;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapperDao {

    Player fromResponse(PlayerResponse response);

    PlayerSearchRequest toSearchRequest(Player player);
}
