package malbingi.spring.rest_api_internet_engineering_1.services;

import malbingi.spring.rest_api_internet_engineering_1.domain.Competition;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompetitionService {

    Flux<Competition> findAll();

    Mono<Competition> getById(String id);

    Mono<Void> create(Publisher<Competition> competitionPublisher);

    Mono<Competition> update(String id, Competition competition);

    Mono delete(String id);
}
