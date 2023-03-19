package com.geopagos.tournament.config;

import com.geopagos.tournament.model.domain.Gender;
import com.geopagos.tournament.model.domain.TournamentAttributes;
import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "application.tournament")
public class TournamentConfig {

    private Map<Gender, List<TournamentAttributes>> defaultAdditionalAttributes;

}
