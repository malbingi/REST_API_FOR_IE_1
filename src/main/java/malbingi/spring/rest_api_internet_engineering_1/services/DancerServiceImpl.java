package malbingi.spring.rest_api_internet_engineering_1.services;

import malbingi.spring.rest_api_internet_engineering_1.domain.Dancer;
import malbingi.spring.rest_api_internet_engineering_1.repositories.DancerRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class DancerServiceImpl implements DancerService {

    private final DancerRepository dancerRepository;

    public DancerServiceImpl(DancerRepository dancerRepository) {
        this.dancerRepository = dancerRepository;
    }

    @Override
    public Flux<Dancer> findAll() {
        return dancerRepository.findAll();
    }

    @Override
    public Mono<Dancer> getById(String id) {
        return dancerRepository.findById(id);
    }

    @Override
    public Mono<Void> create(Publisher<Dancer> dancerPublisher) {
        return dancerRepository.saveAll(dancerPublisher).then();
    }

    @Override
    public Mono update(String id, Dancer dancer) {

        return dancerRepository.findById(id).flatMap(updatedDancer -> {
            updatedDancer.setPointsST(dancer.getPointsST());
            updatedDancer.setPointsLA(dancer.getPointsLA());
            updatedDancer.setClassST(dancer.getClassST());
            updatedDancer.setClassLA(dancer.getClassLA());
            updatedDancer.setFirstName(dancer.getFirstName());
            updatedDancer.setLastName(dancer.getLastName());
            updatedDancer.setStatus(dancer.getStatus());
            return dancerRepository.save(updatedDancer);
        }).map(updatedDancer -> ResponseEntity.ok(updatedDancer)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono delete(String id) {
        final Mono<Dancer> dancerMonoToBeDeleted = getById(id);
        if (Objects.isNull(dancerMonoToBeDeleted)) {
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(dancer -> dancerRepository.delete(dancer).then(Mono.just(dancer)));
    }
}
