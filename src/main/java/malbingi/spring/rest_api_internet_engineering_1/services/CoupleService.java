package malbingi.spring.rest_api_internet_engineering_1.services;

import malbingi.spring.rest_api_internet_engineering_1.domain.Couple;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoupleService {

    Flux<Couple> findAll();

    Mono<Couple> getById(String id);

    Mono<Void> create(Publisher<Couple> couplePublisher);

    Mono<Couple> update(String id, Couple couple);

    Mono delete(String id);
}
