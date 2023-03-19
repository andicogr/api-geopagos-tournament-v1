package com.geopagos.tournament.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geopagos.tournament.business.TestUtil;
import com.geopagos.tournament.config.TournamentConfig;
import com.geopagos.tournament.dao.LuckyDao;
import com.geopagos.tournament.dao.repository.api.PlayerRepository;
import com.geopagos.tournament.dao.repository.db.TournamentRepository;
import com.geopagos.tournament.model.api.Attributes;
import com.geopagos.tournament.model.api.Gender;
import com.geopagos.tournament.model.api.ModelApiException;
import com.geopagos.tournament.model.api.TournamentResponse;
import com.geopagos.tournament.model.api.TournamentResultResponse;
import com.geopagos.tournament.model.api.TournamentStartRequest;
import com.geopagos.tournament.model.api.TournamentsResponse;
import com.geopagos.tournament.model.domain.TournamentAttributes;
import com.geopagos.tournament.model.domain.TournamentConfiguration;
import com.geopagos.tournament.model.thridparty.api.PlayerResponse;
import com.geopagos.tournament.model.thridparty.db.TournamentDocument;
import com.geopagos.tournament.model.thridparty.db.TournamentLevel;
import com.geopagos.tournament.model.thridparty.db.TournamentMatch;
import com.geopagos.tournament.model.thridparty.db.TournamentPlayer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TournamentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private TournamentRepository tournamentRepository;

    @MockBean
    private TournamentConfig tournamentConfig;

    @MockBean
    private LuckyDao luckyDao;


    @Test
    void Given_TournamentId_When_TheTournamentExists_Then_ReturnTournamentInformation() throws Exception {
        String id = UUID.randomUUID().toString();
        String playerId1 = UUID.randomUUID().toString();
        String playerId2 = UUID.randomUUID().toString();

        TournamentDocument tournamentDocument = new TournamentDocument();
        tournamentDocument.setId(id);
        tournamentDocument.setGender("MALE");
        tournamentDocument.setDate("2023-03-19");
        tournamentDocument.setParticipants(2);
        tournamentDocument.setAttributes(List.of("SPEED"));

        //Tournament Player1
        String player1Name = "Player Name " + TestUtil.getUniqueValue();
        TournamentPlayer tournamentPlayer1 = new TournamentPlayer();
        tournamentPlayer1.setId(playerId1);
        tournamentPlayer1.setName(player1Name);

        //Tournament Player2
        String player2Name = "Player Name " + TestUtil.getUniqueValue();
        TournamentPlayer tournamentPlayer2 = new TournamentPlayer();
        tournamentPlayer2.setId(playerId2);
        tournamentPlayer2.setName(player2Name);

        //Set Winner
        tournamentDocument.setWinner(tournamentPlayer1);

        //Tournament Levels
        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        TournamentLevel tournamentLevel = new TournamentLevel();
        tournamentLevel.setLevel(1);

        List<TournamentMatch> tournamentMatches = new ArrayList<>();
        TournamentMatch match = new TournamentMatch();
        match.setPlayer1(tournamentPlayer1);
        match.setPlayer2(tournamentPlayer2);
        match.setWinner(tournamentPlayer1);


        tournamentMatches.add(match);

        tournamentLevel.setMatches(tournamentMatches);

        tournamentLevels.add(tournamentLevel);

        tournamentDocument.setLevels(tournamentLevels);



        Mockito.when(tournamentRepository.findById(id)).thenReturn(Optional.of(tournamentDocument));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/tournaments/{tournamentId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TournamentResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), TournamentResponse.class);

        assertEquals(id, response.getId());
        assertEquals("MALE", response.getGender().name());
        assertEquals("2023-03-19", response.getDate());
        assertEquals(2, response.getParticipants());
        assertEquals(List.of(Attributes.SPEED), response.getAttributes());
        assertEquals(playerId1, response.getWinner().getId());
        assertEquals(player1Name, response.getWinner().getName());
        //Validate Levels
        assertEquals(1, response.getLevels().size());
        assertEquals(1, response.getLevels().get(0).getLevel());
        assertEquals(1, response.getLevels().get(0).getMatches().size());
        //Validate Match
        assertEquals(playerId1, response.getLevels().get(0).getMatches().get(0).getPlayer1().getId());
        assertEquals(player1Name, response.getLevels().get(0).getMatches().get(0).getPlayer1().getName());
        assertEquals(playerId2, response.getLevels().get(0).getMatches().get(0).getPlayer2().getId());
        assertEquals(player2Name, response.getLevels().get(0).getMatches().get(0).getPlayer2().getName());
        assertEquals(playerId1, response.getLevels().get(0).getMatches().get(0).getWinner().getId());
        assertEquals(player1Name, response.getLevels().get(0).getMatches().get(0).getWinner().getName());

    }

    @Test
    void Given_TournamentId_When_TheTournamentNotExists_Then_ReturnException() throws Exception {
        String id = UUID.randomUUID().toString();

        Mockito.when(tournamentRepository.findById(id)).thenReturn(Optional.empty());

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/tournaments/{playerId}", id)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();

        ModelApiException response = objectMapper.readValue(result.getResponse().getContentAsString(), ModelApiException.class);

        assertEquals("00090", response.getCode());
        assertEquals("The record was not found", response.getDescription());

    }

    @Test
    void Given_TournamentsSearch_When_GenderIsFemale_Then_ReturnOnlyFemaleTournaments() throws Exception {
        String id = UUID.randomUUID().toString();
        String playerId1 = UUID.randomUUID().toString();
        String playerId2 = UUID.randomUUID().toString();

        TournamentDocument tournamentDocument = new TournamentDocument();
        tournamentDocument.setId(id);
        tournamentDocument.setGender("FEMALE");
        tournamentDocument.setDate("2023-03-19");
        tournamentDocument.setParticipants(2);
        tournamentDocument.setAttributes(List.of("SPEED"));

        //Tournament Player1
        String player1Name = "Player Name " + TestUtil.getUniqueValue();
        TournamentPlayer tournamentPlayer1 = new TournamentPlayer();
        tournamentPlayer1.setId(playerId1);
        tournamentPlayer1.setName(player1Name);

        //Tournament Player2
        String player2Name = "Player Name " + TestUtil.getUniqueValue();
        TournamentPlayer tournamentPlayer2 = new TournamentPlayer();
        tournamentPlayer2.setId(playerId2);
        tournamentPlayer2.setName(player2Name);

        //Set Winner
        tournamentDocument.setWinner(tournamentPlayer1);

        //Tournament Levels
        List<TournamentLevel> tournamentLevels = new ArrayList<>();

        TournamentLevel tournamentLevel = new TournamentLevel();
        tournamentLevel.setLevel(1);

        List<TournamentMatch> tournamentMatches = new ArrayList<>();
        TournamentMatch match = new TournamentMatch();
        match.setPlayer1(tournamentPlayer1);
        match.setPlayer2(tournamentPlayer2);
        match.setWinner(tournamentPlayer1);


        tournamentMatches.add(match);

        tournamentLevel.setMatches(tournamentMatches);

        tournamentLevels.add(tournamentLevel);

        tournamentDocument.setLevels(tournamentLevels);

        Mockito.when(tournamentRepository.findAll(Mockito.any(Example.class))).thenReturn(List.of(tournamentDocument));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/tournaments")
                        .queryParam("gender", "FEMALE")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TournamentsResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(id, response.get(0).getId());
        assertEquals("FEMALE", response.get(0).getGender().name());
        assertEquals("2023-03-19", response.get(0).getDate());
        assertEquals(2, response.get(0).getParticipants());
        assertEquals(playerId1, response.get(0).getWinner().getId());
        assertEquals(player1Name, response.get(0).getWinner().getName());

    }

    @Test
    void Given_Tournaments_When_StartWithWrongNumberOfParticipants_Then_ReturnError() throws Exception {

        TournamentStartRequest tournamentStartRequest = new TournamentStartRequest();
        tournamentStartRequest.setGender(Gender.FEMALE);

        Mockito.when(playerRepository.findPlayers(Mockito.any())).thenReturn(ResponseEntity.ok(TestUtil.buildRandomPlayerResponseList(com.geopagos.tournament.model.thridparty.api.Gender.FEMALE, 5)));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/tournaments/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(tournamentStartRequest))
        ).andExpect(MockMvcResultMatchers.status().is(412))
                .andReturn();

        ModelApiException response = objectMapper.readValue(result.getResponse().getContentAsString(), ModelApiException.class);

        assertEquals("00010", response.getCode());
        assertEquals("The tournament participants must be power of two, the current Player participants are 5", response.getDescription());

    }

    @Test
    void Given_TournamentsByPlayerIds_When_ThePlayerContainsMixedGenders_Then_ReturnError() throws Exception {

        TournamentStartRequest tournamentStartRequest = new TournamentStartRequest();
        tournamentStartRequest.setGender(Gender.FEMALE);
        tournamentStartRequest.setParticipants(List.of("PLAYER1", "PLAYER2"));

        PlayerResponse player1 = new PlayerResponse();
        player1.setGender(com.geopagos.tournament.model.thridparty.api.Gender.FEMALE);

        PlayerResponse player2 = new PlayerResponse();
        player2.setGender(com.geopagos.tournament.model.thridparty.api.Gender.MALE);

        Mockito.when(playerRepository.findPlayerById("PLAYER1")).thenReturn(ResponseEntity.ok(player1));
        Mockito.when(playerRepository.findPlayerById("PLAYER2")).thenReturn(ResponseEntity.ok( player2));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/tournaments/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(tournamentStartRequest))
        ).andExpect(MockMvcResultMatchers.status().is(412))
                .andReturn();

        ModelApiException response = objectMapper.readValue(result.getResponse().getContentAsString(), ModelApiException.class);

        assertEquals("00050", response.getCode());
        assertEquals("The tournament participants gender must be the same", response.getDescription());

    }

    @Test
    void Given_Tournaments_When_Start_Then_ReturnSuccess() throws Exception {

        String tournamentId = UUID.randomUUID().toString();

        TournamentStartRequest tournamentStartRequest = new TournamentStartRequest();
        tournamentStartRequest.setGender(Gender.FEMALE);

        String player1Id = UUID.randomUUID().toString();
        String player1Name = "Player Name " + TestUtil.getUniqueValue();
        PlayerResponse player1 = new PlayerResponse();
        player1.setId(player1Id);
        player1.setGender(com.geopagos.tournament.model.thridparty.api.Gender.FEMALE);
        player1.setName(player1Name);
        player1.setAbility(BigDecimal.valueOf(80));
        player1.setSpeed(BigDecimal.valueOf(80));
        player1.setReactionTime(BigDecimal.valueOf(80));
        player1.setStrength(BigDecimal.valueOf(80));

        String player2Id = UUID.randomUUID().toString();
        String player2Name = "Player Name " + TestUtil.getUniqueValue();
        PlayerResponse player2 = new PlayerResponse();
        player2.setId(player2Id);
        player2.setGender(com.geopagos.tournament.model.thridparty.api.Gender.FEMALE);
        player2.setName(player2Name);
        player2.setAbility(BigDecimal.valueOf(90));
        player2.setSpeed(BigDecimal.valueOf(90));
        player2.setReactionTime(BigDecimal.valueOf(90));
        player2.setStrength(BigDecimal.valueOf(90));


        Mockito.when(playerRepository.findPlayers(Mockito.any())).thenReturn(ResponseEntity.ok(List.of(player1, player2)));
        Mockito.when(tournamentConfig.getDefaultAdditionalAttributes()).thenReturn(Map.of(com.geopagos.tournament.model.domain.Gender.FEMALE, List.of(TournamentAttributes.SPEED)));
        Mockito.when(luckyDao.getLucky()).thenReturn(1.00);

        Mockito.when(tournamentRepository.save(Mockito.any())).thenAnswer(i -> {
            TournamentDocument document = (TournamentDocument) i.getArguments()[0];
            document.setId(tournamentId);
            return document;
        });

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/tournaments/start")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(tournamentStartRequest))
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        TournamentResultResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), TournamentResultResponse.class);

        assertEquals(tournamentId, response.getId());
        assertEquals(player2Id, response.getWinner().getId());
        assertEquals(player2Name, response.getWinner().getName());
        assertEquals(1, response.getAttributes().size());
        assertEquals("SPEED", response.getAttributes().get(0).name());

    }

}
