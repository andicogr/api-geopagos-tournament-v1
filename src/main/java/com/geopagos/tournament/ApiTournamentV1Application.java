package com.geopagos.tournament;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties
public class ApiTournamentV1Application {

    public static void main(String[] args) {
        SpringApplication.run(ApiTournamentV1Application.class, args);
    }

}
