package com.geopagos.tournament.business.steps;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.geopagos.tournament.business.TestUtil;
import com.geopagos.tournament.dao.PlayerDao;
import com.geopagos.tournament.exceptions.InvalidParticipantsGenderException;
import com.geopagos.tournament.exceptions.InvalidTournamentParticipantException;
import com.geopagos.tournament.model.domain.Gender;
import com.geopagos.tournament.model.domain.Player;
import com.geopagos.tournament.model.domain.Tournament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

class ParticipantsValidationStepTest {

    private PlayerDao playerDao;
    private ParticipantsValidationStep sut;

    @BeforeEach
    void setUp() {

        playerDao = Mockito.mock(PlayerDao.class);

        sut = new ParticipantsValidationStep(playerDao);
    }

    @Test
    void Given_ListOfPlayerIds_When_TheValidationIsExecuted_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setParticipants(List.of("ID1", "ID2"));
        context.setTournament(tournament);

        Mockito.when(playerDao.find(List.of("ID1", "ID2"))).thenReturn(TestUtil.buildRandomParticipantList(Gender.MALE, 2));

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_ListOfPlayerIds_When_PlayerHaveDifferentGender_Then_ThrowInvalidParticipantsGenderException() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setParticipants(List.of("ID1", "ID2"));
        context.setTournament(tournament);

        Player player1 = new Player();
        player1.setGender(Gender.MALE);

        Player player2 = new Player();
        player2.setGender(Gender.FEMALE);

        Mockito.when(playerDao.find(List.of("ID1", "ID2"))).thenReturn(List.of(player1, player2));

        assertThrows(InvalidParticipantsGenderException.class, () -> sut.execute(context));

    }

    @Test
    void Given_ListOfPlayerIdsIsNull_When_TheValidationIsExecuted_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setParticipants(null);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(2);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_ListOfPlayerIdsIsEmpty_When_TheValidationIsExecuted_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setParticipants(List.of());
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(2);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs2_When_IsPowerOfTwo_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(2);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs4_When_IsPowerOfTwo_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(4);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs8_When_IsPowerOfTwo_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(8);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs16_When_IsPowerOfTwo_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(16);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs32_When_IsPowerOfTwo_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(32);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs512_When_IsPowerOfTwo_Then_Success() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(512);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertDoesNotThrow(() -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs1_When_IsNotPowerOfTwo_Then_ThrowInvalidTournamentParticipantException() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(1);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertThrows(InvalidTournamentParticipantException.class, () -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs6_When_IsNotPowerOfTwo_Then_ThrowInvalidTournamentParticipantException() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(6);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertThrows(InvalidTournamentParticipantException.class, () -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs10_When_IsNotPowerOfTwo_Then_ThrowInvalidTournamentParticipantException() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(10);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertThrows(InvalidTournamentParticipantException.class, () -> sut.execute(context));

    }

    @Test
    void Given_NumberOfParticipantsIs12_When_IsNotPowerOfTwo_Then_ThrowInvalidTournamentParticipantException() {

        TournamentContext context = new TournamentContext();
        Tournament tournament = new Tournament();
        tournament.setGender(Gender.MALE);
        context.setTournament(tournament);

        List mockList = Mockito.mock(List.class);
        Mockito.when(mockList.size()).thenReturn(12);
        Mockito.when(playerDao.find(Mockito.any(Player.class))).thenReturn(mockList);

        assertThrows(InvalidTournamentParticipantException.class, () -> sut.execute(context));

    }

}