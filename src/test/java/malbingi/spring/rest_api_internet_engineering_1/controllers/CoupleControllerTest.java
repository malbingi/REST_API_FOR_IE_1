package malbingi.spring.rest_api_internet_engineering_1.controllers;

import malbingi.spring.rest_api_internet_engineering_1.domain.Couple;
import malbingi.spring.rest_api_internet_engineering_1.repositories.CoupleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

class CoupleControllerTest {

    CoupleController coupleController;
    CoupleRepository coupleRepository;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        coupleController = Mockito.mock(CoupleController.class);
        coupleRepository = Mockito.mock(CoupleRepository.class);
        webTestClient = WebTestClient.bindToController(coupleController).build();
    }

    @Test
    void list() {
        BDDMockito.given(coupleRepository.findAll()).willReturn(Flux.just(Couple.builder().build(), Couple.builder().build()));
        webTestClient.get().uri("/couples").exchange().expectBodyList(Couple.class).hasSize(2);
    }

    @Test
    void getById() {
        BDDMockito.given(coupleRepository.findById("randomId")).willReturn(Mono.just(Couple.builder().build()));
        webTestClient.get().uri("/couples/randomId").exchange().expectBody(Couple.class);
    }

    @Test
    void create() {
        BDDMockito.given(coupleRepository.saveAll(any(Publisher.class))).willReturn(Flux.just(Couple.builder().build()));
        Mono<Couple> coupleMonoToCreate = Mono.just(Couple.builder().build());
        webTestClient.post().uri("/couples").body(coupleMonoToCreate, Couple.class).exchange().expectStatus().isCreated();
    }

    @Test
    void update() {

        BDDMockito.given(coupleRepository.save(any(Couple.class))).willReturn(Mono.just(Couple.builder().build()));
        Mono<Couple> coupleMonoToBeUpdated = Mono.just(Couple.builder().build());
        webTestClient.put().uri("/couples/someRandomId").body(coupleMonoToBeUpdated, Couple.class).exchange().expectStatus().isOk();
    }

    @Test
    void delete() {
    }
}