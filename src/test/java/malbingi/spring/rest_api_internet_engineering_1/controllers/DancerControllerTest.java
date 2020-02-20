package malbingi.spring.rest_api_internet_engineering_1.controllers;

import malbingi.spring.rest_api_internet_engineering_1.domain.CLASS;
import malbingi.spring.rest_api_internet_engineering_1.domain.Dancer;
import malbingi.spring.rest_api_internet_engineering_1.domain.STATUS;
import malbingi.spring.rest_api_internet_engineering_1.repositories.DancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;

class DancerControllerTest {

    DancerController dancerController;
    DancerRepository dancerRepository;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {

        dancerRepository = Mockito.mock(DancerRepository.class);
        dancerController = Mockito.mock(DancerController.class);
        webTestClient = WebTestClient.bindToController(dancerController).build();
    }

    @Test
    void list() {

        BDDMockito.given(dancerRepository.findAll()).willReturn(Flux.just(Dancer.builder().firstName("Jan").lastName("Jan").classLA(CLASS.S).classST(CLASS.B).dateOfJoin(LocalDate.now()).pointsLA(0).pointsST(0).status(STATUS.Ordinary).build(), Dancer.builder().firstName("Jan").lastName("Jan").classLA(CLASS.S).classST(CLASS.B).dateOfJoin(LocalDate.now()).pointsLA(0).pointsST(0).status(STATUS.Ordinary).build()));
        webTestClient.get().uri("/dancers").exchange().expectBodyList(Dancer.class).hasSize(2);
    }

    @Test
    void getById() {

        BDDMockito.given(dancerRepository.findById("randomID")).willReturn(Mono.just(Dancer.builder().firstName("Frytka").build()));
        webTestClient.get().uri("/dancers/randomID").exchange().expectBody(Dancer.class);
    }

    @Test
    void create() {

        BDDMockito.given(dancerRepository.saveAll(any(Publisher.class))).willReturn(Flux.just(Dancer.builder().firstName("Jakieś").build()));
        Mono<Dancer> dancerMonoToSave = Mono.just(Dancer.builder().firstName("Rysiek").build());
        webTestClient.post().uri("/dancers").body(dancerMonoToSave, Dancer.class).exchange().expectStatus().isCreated();
    }

    @Test
    void update() {

        BDDMockito.given(dancerRepository.save(any(Dancer.class))).willReturn(Mono.just(Dancer.builder().build()));
        Mono<Dancer> dancerMonoToBeUpdated = Mono.just(Dancer.builder().firstName("Testowe").build());
        webTestClient.put().uri("/dancers/someRandomId").body(dancerMonoToBeUpdated, Dancer.class).exchange().expectStatus().isOk();
    }

    @Test
    void delete() {


    }
}