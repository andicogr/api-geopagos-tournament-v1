package com.geopagos.tournament.business.steps;

import static org.junit.jupiter.api.Assertions.*;

import com.geopagos.tournament.business.TestUtil;
import com.geopagos.tournament.model.domain.Gender;
import com.geopagos.tournament.model.domain.Match;
import com.geopagos.tournament.model.domain.Tournament;
import com.geopagos.tournament.model.domain.TournamentLevel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AssignPlayersToMatchesStepTest {

    private AssignPlayersToMatchesStep sut;

    @BeforeEach
    void setUp() {

        sut = new AssignPlayersToMatchesStep();
    }

    @Test
    void Given_TournamentWith2PlayersAndLevelsAndMatchesDefined_When_PlayersAreAssignedToTheMatches_Then_EachPlayerMustBeAssignedOnce() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        TournamentLevel tournamentLevel = new TournamentLevel();
        tournamentLevel.setLevel(1);

        List<Match> matches = new ArrayList<>();

        Match match1 = new Match();
        match1.setCode("LEVEL1-00001");
        matches.add(match1);

        tournamentLevel.setMatches(matches);

        tournamentLevels.add(tournamentLevel);

        tournament.setLevels(tournamentLevels);

        context.setTournament(tournament);
        context.setPlayers(TestUtil.buildRandomParticipantList(Gender.MALE, 2));

        sut.execute(context);

        int firstLevel = context.getTournament().getLevels().stream().map(TournamentLevel::getLevel).mapToInt(v -> v).max().getAsInt();

        context.getPlayers().forEach(player -> {

            int timesAssigned = 0;

            TournamentLevel firstTournamentLevel = context.getTournament().getLevels().stream().filter(item -> item.getLevel() == firstLevel).findFirst().get();

            for (Match match: firstTournamentLevel.getMatches()) {
                if (match.getPlayer1().getId().equals(player.getId())){
                    timesAssigned++;
                }

                if (match.getPlayer2().getId().equals(player.getId())){
                    timesAssigned++;
                }
            }

            assertEquals(1, timesAssigned, "The Player " + player.getName() + "is assigned " + timesAssigned + " times");

        });
    }

    @Test
    void Given_TournamentWith4PlayersAndLevelsAndMatchesDefined_When_PlayersAreAssignedToTheMatches_Then_EachPlayerMustBeAssignedOnce() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        //Set tournament level 2
        TournamentLevel tournamentLevel2 = new TournamentLevel();
        tournamentLevel2.setLevel(2);

        List<Match> matchesLevel2 = new ArrayList<>();

        Match match1 = new Match();
        match1.setCode("LEVEL2-00001");
        matchesLevel2.add(match1);

        Match match2 = new Match();
        match2.setCode("LEVEL2-00002");
        matchesLevel2.add(match2);

        tournamentLevel2.setMatches(matchesLevel2);

        tournamentLevels.add(tournamentLevel2);

        //Set tournament level 1
        TournamentLevel tournamentLevel1 = new TournamentLevel();
        tournamentLevel1.setLevel(1);

        List<Match> matches = new ArrayList<>();

        Match match3 = new Match();
        match3.setCode("LEVEL1-00001");
        matches.add(match1);

        tournamentLevel1.setMatches(matches);

        tournamentLevels.add(tournamentLevel1);

        tournament.setLevels(tournamentLevels);

        context.setTournament(tournament);
        context.setPlayers(TestUtil.buildRandomParticipantList(Gender.MALE, 4));

        sut.execute(context);

        int firstLevel = context.getTournament().getLevels().stream().map(TournamentLevel::getLevel).mapToInt(v -> v).max().getAsInt();

        context.getPlayers().forEach(player -> {

            int timesAssigned = 0;

            TournamentLevel firstTournamentLevel = context.getTournament().getLevels().stream().filter(item -> item.getLevel() == firstLevel).findFirst().get();

            for (Match match: firstTournamentLevel.getMatches()) {
                if (match.getPlayer1().getId().equals(player.getId())){
                    timesAssigned++;
                }

                if (match.getPlayer2().getId().equals(player.getId())){
                    timesAssigned++;
                }
            }

            assertEquals(1, timesAssigned, "The Player " + player.getName() + "is assigned " + timesAssigned + " times");

        });
    }

    @Test
    void Given_TournamentWith8PlayersAndLevelsAndMatchesDefined_When_PlayersAreAssignedToTheMatches_Then_EachPlayerMustBeAssignedOnce() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        //Set tournament level 3
        TournamentLevel tournamentLevel3 = new TournamentLevel();
        tournamentLevel3.setLevel(3);

        List<Match> matchesLevel3 = new ArrayList<>();

        Match match1 = new Match();
        match1.setCode("LEVEL3-00001");
        matchesLevel3.add(match1);

        Match match2 = new Match();
        match2.setCode("LEVEL3-00002");
        matchesLevel3.add(match2);

        Match match3 = new Match();
        match3.setCode("LEVEL3-00003");
        matchesLevel3.add(match3);

        Match match4 = new Match();
        match4.setCode("LEVEL3-00004");
        matchesLevel3.add(match4);

        tournamentLevel3.setMatches(matchesLevel3);

        tournamentLevels.add(tournamentLevel3);

        //Set tournament level 2
        TournamentLevel tournamentLevel2 = new TournamentLevel();
        tournamentLevel2.setLevel(2);

        List<Match> matchesLevel2 = new ArrayList<>();

        Match match5 = new Match();
        match5.setCode("LEVEL2-00001");
        matchesLevel2.add(match5);

        Match match6 = new Match();
        match6.setCode("LEVEL2-00002");
        matchesLevel2.add(match6);

        tournamentLevel2.setMatches(matchesLevel2);

        tournamentLevels.add(tournamentLevel2);

        //Set tournament level 1
        TournamentLevel tournamentLevel1 = new TournamentLevel();
        tournamentLevel1.setLevel(1);

        List<Match> matches = new ArrayList<>();

        Match match7 = new Match();
        match7.setCode("LEVEL1-00001");
        matches.add(match7);

        tournamentLevel1.setMatches(matches);

        tournamentLevels.add(tournamentLevel1);

        tournament.setLevels(tournamentLevels);

        context.setTournament(tournament);
        context.setPlayers(TestUtil.buildRandomParticipantList(Gender.MALE, 8));

        sut.execute(context);

        int firstLevel = context.getTournament().getLevels().stream().map(TournamentLevel::getLevel).mapToInt(v -> v).max().getAsInt();

        context.getPlayers().forEach(player -> {

            int timesAssigned = 0;

            TournamentLevel firstTournamentLevel = context.getTournament().getLevels().stream().filter(item -> item.getLevel() == firstLevel).findFirst().get();

            for (Match match: firstTournamentLevel.getMatches()) {
                if (match.getPlayer1().getId().equals(player.getId())){
                    timesAssigned++;
                }

                if (match.getPlayer2().getId().equals(player.getId())){
                    timesAssigned++;
                }
            }

            assertEquals(1, timesAssigned, "The Player " + player.getName() + "is assigned " + timesAssigned + " times");

        });
    }

}