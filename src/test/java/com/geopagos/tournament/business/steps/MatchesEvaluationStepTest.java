package com.geopagos.tournament.business.steps;

import static org.junit.jupiter.api.Assertions.*;

import com.geopagos.tournament.business.TestUtil;
import com.geopagos.tournament.config.TournamentConfig;
import com.geopagos.tournament.dao.LuckyDao;
import com.geopagos.tournament.model.domain.Gender;
import com.geopagos.tournament.model.domain.Match;
import com.geopagos.tournament.model.domain.Player;
import com.geopagos.tournament.model.domain.Tournament;
import com.geopagos.tournament.model.domain.TournamentAttributes;
import com.geopagos.tournament.model.domain.TournamentLevel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MatchesEvaluationStepTest {

    private TournamentConfig tournamentConfig;
    private LuckyDao luckyDao;

    private MatchesEvaluationStep sut;

    @BeforeEach
    void setUp() {

        tournamentConfig = Mockito.mock(TournamentConfig.class);
        luckyDao = Mockito.mock(LuckyDao.class);

        sut = new MatchesEvaluationStep(tournamentConfig, luckyDao);

        Map<Gender, List<TournamentAttributes>> configMap = new HashMap<>();
        configMap.put(Gender.MALE, List.of(TournamentAttributes.SPEED, TournamentAttributes.STRENGTH));
        configMap.put(Gender.FEMALE, List.of(TournamentAttributes.REACTION_TIME));

        Mockito.when(tournamentConfig.getDefaultAdditionalAttributes()).thenReturn(configMap);
    }

    @Test
    void Given_2Participant_When_TheMatchesAreEvaluatedWithOutLucky_Then_AWinnerShouldBeDefinedBasedOnTheAttributes() {

        TournamentContext context = new TournamentContext();

        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        //Set Tournament Level 1
        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        TournamentLevel tournamentLevel = new TournamentLevel();
        tournamentLevel.setLevel(1);

        List<Match> matches = new ArrayList<>();

        Match match1 = new Match();
        match1.setCode("LEVEL1-00001");

        Player player1 = new Player();
        player1.setId("0000099991");
        player1.setGender(Gender.MALE);
        player1.setName("Player2");
        player1.setAbility(90);
        player1.setSpeed(90);
        player1.setReactionTime(90);
        player1.setStrength(90);
        match1.setPlayer1(player1);

        Player player2 = new Player();
        player2.setId("0000099992");
        player2.setGender(Gender.MALE);
        player2.setName("Player2");
        player2.setAbility(80);
        player2.setSpeed(80);
        player2.setReactionTime(80);
        player2.setStrength(80);
        match1.setPlayer2(player2);

        matches.add(match1);

        tournamentLevel.setMatches(matches);

        tournamentLevels.add(tournamentLevel);
        //End set Tournament Level 1

        tournament.setLevels(tournamentLevels);

        context.setTournament(tournament);

        Mockito.when(luckyDao.getLucky()).thenReturn(1.40).thenReturn(1.40);

        sut.execute(context);

        assertEquals("0000099991", context.getTournament().getWinner().getId());
    }

    @Test
    void Given_2Participant_When_TheMatchesAreEvaluatedWithLucky_Then_AWinnerShouldBeDefinedBasedOnTheLucky() {

        TournamentContext context = new TournamentContext();

        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        //Set Tournament Level 1
        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        TournamentLevel tournamentLevel = new TournamentLevel();
        tournamentLevel.setLevel(1);

        List<Match> matches = new ArrayList<>();

        Match match1 = new Match();
        match1.setCode("LEVEL1-00001");

        Player player1 = new Player();
        player1.setId("0000099991");
        player1.setGender(Gender.MALE);
        player1.setName("Player2");
        player1.setAbility(90);
        player1.setSpeed(90);
        player1.setReactionTime(90);
        player1.setStrength(90);
        match1.setPlayer1(player1);

        Player player2 = new Player();
        player2.setId("0000099992");
        player2.setGender(Gender.MALE);
        player2.setName("Player2");
        player2.setAbility(80);
        player2.setSpeed(80);
        player2.setReactionTime(80);
        player2.setStrength(80);
        match1.setPlayer2(player2);

        matches.add(match1);

        tournamentLevel.setMatches(matches);

        tournamentLevels.add(tournamentLevel);
        //End set Tournament Level 1

        tournament.setLevels(tournamentLevels);

        context.setTournament(tournament);

        Mockito.when(luckyDao.getLucky()).thenReturn(1.10).thenReturn(1.50);

        sut.execute(context);

        assertEquals("0000099992", context.getTournament().getWinner().getId());
    }

    @Test
    void Given_4Participant_When_TheMatchesAreEvaluatedWithOutLucky_Then_AWinnerShouldBeDefinedBasedOnTheAttributes() {

        TournamentContext context = new TournamentContext();

        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        //Set Tournament Level 2
        TournamentLevel tournamentLevel2 = new TournamentLevel();
        tournamentLevel2.setLevel(2);

        List<Match> matches = new ArrayList<>();

        //First Match - Level 2
        Match match1 = new Match();
        match1.setCode("LEVEL2-00001");

        Player player1 = new Player();
        player1.setId("000009999-1");
        player1.setGender(Gender.MALE);
        player1.setName("Player2");
        player1.setAbility(90);
        player1.setSpeed(90);
        player1.setReactionTime(90);
        player1.setStrength(90);
        match1.setPlayer1(player1);

        Player player2 = new Player();
        player2.setId("000009999-2");
        player2.setGender(Gender.MALE);
        player2.setName("Player2");
        player2.setAbility(80);
        player2.setSpeed(80);
        player2.setReactionTime(80);
        player2.setStrength(80);
        match1.setPlayer2(player2);

        matches.add(match1);

        //Second Match - Level 2
        Match match2 = new Match();
        match2.setCode("LEVEL2-00002");

        Player player3 = new Player();
        player3.setId("000009999-3");
        player3.setGender(Gender.MALE);
        player3.setName("Player2");
        player3.setAbility(90);
        player3.setSpeed(90);
        player3.setReactionTime(90);
        player3.setStrength(90);
        match2.setPlayer1(player3);

        Player player4 = new Player();
        player4.setId("000009999-4");
        player4.setGender(Gender.MALE);
        player4.setName("Player2");
        player4.setAbility(95);
        player4.setSpeed(95);
        player4.setReactionTime(95);
        player4.setStrength(95);
        match2.setPlayer2(player4);

        matches.add(match2);

        tournamentLevel2.setMatches(matches);

        tournamentLevels.add(tournamentLevel2);
        //End set Tournament Level 2

        //Set Tournament Level 1
        TournamentLevel tournamentLevel1 = new TournamentLevel();
        tournamentLevel1.setLevel(1);

        List<Match> matches2 = new ArrayList<>();

        //First Match - Level 2
        Match match3 = new Match();
        match3.setCode("LEVEL1-00001");
        match3.setPlayer1MatchCode("LEVEL2-00001");
        match3.setPlayer2MatchCode("LEVEL2-00002");


        matches2.add(match3);

        tournamentLevel1.setMatches(matches2);

        tournamentLevels.add(tournamentLevel1);
        //End set Tournament Level 1

        tournament.setLevels(tournamentLevels);

        context.setTournament(tournament);

        Mockito.when(luckyDao.getLucky()).thenReturn(1.40);

        sut.execute(context);

        assertEquals("000009999-4", context.getTournament().getWinner().getId());
    }

    @Test
    void Given_4Participant_When_TheMatchesAreEvaluatedWithLucky_Then_AWinnerShouldBeDefinedBasedOnTheLucky() {

        TournamentContext context = new TournamentContext();

        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        //Set Tournament Level 2
        TournamentLevel tournamentLevel2 = new TournamentLevel();
        tournamentLevel2.setLevel(2);

        List<Match> matches = new ArrayList<>();

        //First Match - Level 2
        Match match1 = new Match();
        match1.setCode("LEVEL2-00001");

        Player player1 = new Player();
        player1.setId("000009999-1");
        player1.setGender(Gender.MALE);
        player1.setName("Player2");
        player1.setAbility(90);
        player1.setSpeed(90);
        player1.setReactionTime(90);
        player1.setStrength(90);
        match1.setPlayer1(player1);

        Player player2 = new Player();
        player2.setId("000009999-2");
        player2.setGender(Gender.MALE);
        player2.setName("Player2");
        player2.setAbility(80);
        player2.setSpeed(80);
        player2.setReactionTime(80);
        player2.setStrength(80);
        match1.setPlayer2(player2);

        matches.add(match1);

        //Second Match - Level 2
        Match match2 = new Match();
        match2.setCode("LEVEL2-00002");

        Player player3 = new Player();
        player3.setId("000009999-3");
        player3.setGender(Gender.MALE);
        player3.setName("Player2");
        player3.setAbility(70);
        player3.setSpeed(70);
        player3.setReactionTime(70);
        player3.setStrength(70);
        match2.setPlayer1(player3);

        Player player4 = new Player();
        player4.setId("000009999-4");
        player4.setGender(Gender.MALE);
        player4.setName("Player2");
        player4.setAbility(77);
        player4.setSpeed(77);
        player4.setReactionTime(77);
        player4.setStrength(77);
        match2.setPlayer2(player4);

        matches.add(match2);

        tournamentLevel2.setMatches(matches);

        tournamentLevels.add(tournamentLevel2);
        //End set Tournament Level 2

        //Set Tournament Level 1
        TournamentLevel tournamentLevel1 = new TournamentLevel();
        tournamentLevel1.setLevel(1);

        List<Match> matches2 = new ArrayList<>();

        //First Match - Level 2
        Match match3 = new Match();
        match3.setCode("LEVEL1-00001");
        match3.setPlayer1MatchCode("LEVEL2-00001");
        match3.setPlayer2MatchCode("LEVEL2-00002");


        matches2.add(match3);

        tournamentLevel1.setMatches(matches2);

        tournamentLevels.add(tournamentLevel1);
        //End set Tournament Level 1

        tournament.setLevels(tournamentLevels);

        context.setTournament(tournament);

        Mockito.when(luckyDao.getLucky()).thenReturn(1.10).thenReturn(1.10).thenReturn(1.50).thenReturn(1.10).thenReturn(1.10).thenReturn(1.50);

        sut.execute(context);

        assertEquals("000009999-3", context.getTournament().getWinner().getId());
    }


}