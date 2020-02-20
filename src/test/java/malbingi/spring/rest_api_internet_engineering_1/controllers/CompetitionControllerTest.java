package malbingi.spring.rest_api_internet_engineering_1.controllers;

import malbingi.spring.rest_api_internet_engineering_1.domain.Competition;
import malbingi.spring.rest_api_internet_engineering_1.domain.Couple;
import malbingi.spring.rest_api_internet_engineering_1.repositories.CompetitionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

class CompetitionControllerTest {

    CompetitionController competitionController;
    CompetitionRepository competitionRepository;
    WebTestClient webTestClient;

    @BeforeEach
    void setUp() {

        competitionController = Mockito.mock(CompetitionController.class);
        competitionRepository = Mockito.mock(CompetitionRepository.class);
        webTestClient = WebTestClient.bindToController(competitionController).build();
    }

    @Test
    void list() {
        BDDMockito.given(competitionRepository.findAll()).willReturn(Flux.just(Competition.builder().build(), Competition.builder().build()));
        webTestClient.get().uri("/competitions").exchange().expectBodyList(Couple.class).hasSize(2);
    }

    @Test
    void getById() {
        BDDMockito.given(competitionRepository.findById("randomId")).willReturn(Mono.just(Competition.builder().build()));
        webTestClient.get().uri("/competitions/randomId").exchange().expectBody(Competition.class);
    }

    @Test
    void create() {

        BDDMockito.given(competitionRepository.saveAll(any(Publisher.class))).willReturn(Flux.just(Competition.builder().build()));
        Mono<Competition> competitionMonoToBeCreated = Mono.just(Competition.builder().build());
        webTestClient.post().uri("/competitions").body(competitionMonoToBeCreated, Competition.class).exchange().expectStatus().isCreated();
    }

    @Test
    void update() {
        BDDMockito.given(competitionRepository.save(any(Competition.class))).willReturn(Mono.just(Competition.builder().build()));
        Mono<Competition> competitionMonoToBeUpdated = Mono.just(Competition.builder().build());
        webTestClient.put().uri("/competitions/someRandomId").body(competitionMonoToBeUpdated, Competition.class).exchange().expectStatus().isOk();
    }

    @Test
    void delete() {
    }
}