package com.geopagos.tournament.business.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.geopagos.tournament.business.TestUtil;
import com.geopagos.tournament.model.domain.Gender;
import com.geopagos.tournament.model.domain.Tournament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefineTournamentScheduleStepTest {

    private DefineTournamentScheduleStep sut;

    @BeforeEach
    void setUp() {

        sut = new DefineTournamentScheduleStep();
    }

    @Test
    void Given_2Participant_When_TheTournamentScheduleIsDefined_Then_CreateOneTournamentLevel() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        context.setTournament(tournament);
        context.setPlayers(TestUtil.buildRandomParticipantList(Gender.MALE, 2));

        sut.execute(context);

        assertEquals(1, context.getTournament().getLevels().size());

        assertEquals(1, context.getTournament().getLevels().get(0).getLevel());
        assertEquals(1, context.getTournament().getLevels().get(0).getMatches().size());

        assertEquals("LEVEL1-00001", context.getTournament().getLevels().get(0).getMatches().get(0).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer2MatchCode());

    }

    @Test
    void Given_4Participant_When_TheTournamentScheduleIsDefined_Then_CreateOneTournamentLevel() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        context.setTournament(tournament);
        context.setPlayers(TestUtil.buildRandomParticipantList(Gender.MALE, 4));

        sut.execute(context);

        assertEquals(2, context.getTournament().getLevels().size());

        assertEquals(2, context.getTournament().getLevels().get(0).getLevel());
        assertEquals(2, context.getTournament().getLevels().get(0).getMatches().size());

        assertEquals("LEVEL2-00001", context.getTournament().getLevels().get(0).getMatches().get(0).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer2MatchCode());

        assertEquals("LEVEL2-00002", context.getTournament().getLevels().get(0).getMatches().get(1).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(1).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(1).getPlayer2MatchCode());

        assertEquals(1, context.getTournament().getLevels().get(1).getLevel());
        assertEquals(1, context.getTournament().getLevels().get(1).getMatches().size());

        assertEquals("LEVEL1-00001", context.getTournament().getLevels().get(1).getMatches().get(0).getCode());
        assertEquals("LEVEL2-00001", context.getTournament().getLevels().get(1).getMatches().get(0).getPlayer1MatchCode());
        assertEquals("LEVEL2-00002", context.getTournament().getLevels().get(1).getMatches().get(0).getPlayer2MatchCode());

    }

    @Test
    void Given_8Participant_When_TheTournamentScheduleIsDefined_Then_CreateOneTournamentLevel() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        context.setTournament(tournament);
        context.setPlayers(TestUtil.buildRandomParticipantList(Gender.MALE, 8));

        sut.execute(context);

        assertEquals(3, context.getTournament().getLevels().size());

        assertEquals(3, context.getTournament().getLevels().get(0).getLevel());
        assertEquals(4, context.getTournament().getLevels().get(0).getMatches().size());

        assertEquals("LEVEL3-00001", context.getTournament().getLevels().get(0).getMatches().get(0).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer2MatchCode());

        assertEquals("LEVEL3-00002", context.getTournament().getLevels().get(0).getMatches().get(1).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(1).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(1).getPlayer2MatchCode());

        assertEquals("LEVEL3-00003", context.getTournament().getLevels().get(0).getMatches().get(2).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(2).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(2).getPlayer2MatchCode());

        assertEquals("LEVEL3-00004", context.getTournament().getLevels().get(0).getMatches().get(3).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(3).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(3).getPlayer2MatchCode());

        assertEquals(2, context.getTournament().getLevels().get(1).getLevel());
        assertEquals(2, context.getTournament().getLevels().get(1).getMatches().size());

        assertEquals("LEVEL2-00001", context.getTournament().getLevels().get(1).getMatches().get(0).getCode());
        assertEquals("LEVEL3-00001", context.getTournament().getLevels().get(1).getMatches().get(0).getPlayer1MatchCode());
        assertEquals("LEVEL3-00002", context.getTournament().getLevels().get(1).getMatches().get(0).getPlayer2MatchCode());

        assertEquals("LEVEL2-00002", context.getTournament().getLevels().get(1).getMatches().get(1).getCode());
        assertEquals("LEVEL3-00003", context.getTournament().getLevels().get(1).getMatches().get(1).getPlayer1MatchCode());
        assertEquals("LEVEL3-00004", context.getTournament().getLevels().get(1).getMatches().get(1).getPlayer2MatchCode());

        assertEquals(1, context.getTournament().getLevels().get(2).getLevel());
        assertEquals(1, context.getTournament().getLevels().get(2).getMatches().size());

        assertEquals("LEVEL1-00001", context.getTournament().getLevels().get(2).getMatches().get(0).getCode());
        assertEquals("LEVEL2-00001", context.getTournament().getLevels().get(2).getMatches().get(0).getPlayer1MatchCode());
        assertEquals("LEVEL2-00002", context.getTournament().getLevels().get(2).getMatches().get(0).getPlayer2MatchCode());

    }


    @Test
    void Given_16Participant_When_TheTournamentScheduleIsDefined_Then_CreateOneTournamentLevel() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);

        context.setTournament(tournament);
        context.setPlayers(TestUtil.buildRandomParticipantList(Gender.MALE, 16));

        sut.execute(context);

        assertEquals(4, context.getTournament().getLevels().size());

        assertEquals(4, context.getTournament().getLevels().get(0).getLevel());
        assertEquals(8, context.getTournament().getLevels().get(0).getMatches().size());

        assertEquals("LEVEL4-00001", context.getTournament().getLevels().get(0).getMatches().get(0).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(0).getPlayer2MatchCode());

        assertEquals("LEVEL4-00002", context.getTournament().getLevels().get(0).getMatches().get(1).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(1).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(1).getPlayer2MatchCode());

        assertEquals("LEVEL4-00003", context.getTournament().getLevels().get(0).getMatches().get(2).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(2).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(2).getPlayer2MatchCode());

        assertEquals("LEVEL4-00004", context.getTournament().getLevels().get(0).getMatches().get(3).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(3).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(3).getPlayer2MatchCode());

        assertEquals("LEVEL4-00005", context.getTournament().getLevels().get(0).getMatches().get(4).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(4).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(4).getPlayer2MatchCode());

        assertEquals("LEVEL4-00006", context.getTournament().getLevels().get(0).getMatches().get(5).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(5).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(5).getPlayer2MatchCode());

        assertEquals("LEVEL4-00007", context.getTournament().getLevels().get(0).getMatches().get(6).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(6).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(6).getPlayer2MatchCode());

        assertEquals("LEVEL4-00008", context.getTournament().getLevels().get(0).getMatches().get(7).getCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(7).getPlayer1MatchCode());
        assertNull(context.getTournament().getLevels().get(0).getMatches().get(7).getPlayer2MatchCode());

        assertEquals(3, context.getTournament().getLevels().get(1).getLevel());
        assertEquals(4, context.getTournament().getLevels().get(1).getMatches().size());

        assertEquals("LEVEL3-00001", context.getTournament().getLevels().get(1).getMatches().get(0).getCode());
        assertEquals("LEVEL4-00001", context.getTournament().getLevels().get(1).getMatches().get(0).getPlayer1MatchCode());
        assertEquals("LEVEL4-00002", context.getTournament().getLevels().get(1).getMatches().get(0).getPlayer2MatchCode());

        assertEquals("LEVEL3-00002", context.getTournament().getLevels().get(1).getMatches().get(1).getCode());
        assertEquals("LEVEL4-00003", context.getTournament().getLevels().get(1).getMatches().get(1).getPlayer1MatchCode());
        assertEquals("LEVEL4-00004", context.getTournament().getLevels().get(1).getMatches().get(1).getPlayer2MatchCode());

        assertEquals("LEVEL3-00003", context.getTournament().getLevels().get(1).getMatches().get(2).getCode());
        assertEquals("LEVEL4-00005", context.getTournament().getLevels().get(1).getMatches().get(2).getPlayer1MatchCode());
        assertEquals("LEVEL4-00006", context.getTournament().getLevels().get(1).getMatches().get(2).getPlayer2MatchCode());

        assertEquals("LEVEL3-00004", context.getTournament().getLevels().get(1).getMatches().get(3).getCode());
        assertEquals("LEVEL4-00007", context.getTournament().getLevels().get(1).getMatches().get(3).getPlayer1MatchCode());
        assertEquals("LEVEL4-00008", context.getTournament().getLevels().get(1).getMatches().get(3).getPlayer2MatchCode());

        assertEquals(2, context.getTournament().getLevels().get(2).getLevel());
        assertEquals(2, context.getTournament().getLevels().get(2).getMatches().size());

        assertEquals("LEVEL2-00001", context.getTournament().getLevels().get(2).getMatches().get(0).getCode());
        assertEquals("LEVEL3-00001", context.getTournament().getLevels().get(2).getMatches().get(0).getPlayer1MatchCode());
        assertEquals("LEVEL3-00002", context.getTournament().getLevels().get(2).getMatches().get(0).getPlayer2MatchCode());

        assertEquals("LEVEL2-00002", context.getTournament().getLevels().get(2).getMatches().get(1).getCode());
        assertEquals("LEVEL3-00003", context.getTournament().getLevels().get(2).getMatches().get(1).getPlayer1MatchCode());
        assertEquals("LEVEL3-00004", context.getTournament().getLevels().get(2).getMatches().get(1).getPlayer2MatchCode());

        assertEquals(1, context.getTournament().getLevels().get(3).getLevel());
        assertEquals(1, context.getTournament().getLevels().get(3).getMatches().size());

        assertEquals("LEVEL1-00001", context.getTournament().getLevels().get(3).getMatches().get(0).getCode());
        assertEquals("LEVEL2-00001", context.getTournament().getLevels().get(3).getMatches().get(0).getPlayer1MatchCode());
        assertEquals("LEVEL2-00002", context.getTournament().getLevels().get(3).getMatches().get(0).getPlayer2MatchCode());

    }

}