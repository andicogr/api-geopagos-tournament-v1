package com.geopagos.tournament.business;

import com.geopagos.tournament.model.domain.Gender;
import com.geopagos.tournament.model.domain.Player;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class TestUtil {

    public static List<Player> buildRandomParticipantList(Gender gender, Integer quantity) {

        List<Player> players = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            Player player = new Player();
            player.setId("000009999" + i);
            player.setGender(gender);
            player.setName("Player" + i);
            player.setAbility(getRandomStatistic());
            player.setSpeed(getRandomStatistic());
            player.setReactionTime(getRandomStatistic());
            player.setStrength(getRandomStatistic());

            players.add(player);
        }

        return players;
    }

    public static Player buildRandomParticipant(Gender gender) {

        long uniqueValue = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
        Player player = new Player();
        player.setId("000009999" + uniqueValue);
        player.setGender(gender);
        player.setName("Player" + uniqueValue);
        player.setAbility(getRandomStatistic());
        player.setSpeed(getRandomStatistic());
        player.setReactionTime(getRandomStatistic());
        player.setStrength(getRandomStatistic());

        return player;
    }

    public static int getRandomStatistic() {
        Random r = new Random();
        return r.nextInt(100) + 1;
    }

}
