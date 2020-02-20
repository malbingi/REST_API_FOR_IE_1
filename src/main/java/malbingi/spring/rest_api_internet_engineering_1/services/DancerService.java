package malbingi.spring.rest_api_internet_engineering_1.services;

import malbingi.spring.rest_api_internet_engineering_1.domain.Dancer;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DancerService {

    Flux<Dancer> findAll();

    Mono<Dancer> getById(String id);

    Mono<Void> create(Publisher<Dancer> dancerPublisher);

    Mono<Dancer> update(String id, Dancer dancer);

    Mono delete(String id);

}
