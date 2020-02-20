package malbingi.spring.rest_api_internet_engineering_1.services;

import malbingi.spring.rest_api_internet_engineering_1.domain.Couple;
import malbingi.spring.rest_api_internet_engineering_1.repositories.CoupleRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CoupleServiceImpl implements CoupleService {

    private final CoupleRepository coupleRepository;

    public CoupleServiceImpl(CoupleRepository coupleRepository) {
        this.coupleRepository = coupleRepository;
    }

    @Override
    public Flux<Couple> findAll() {
        return coupleRepository.findAll();
    }

    @Override
    public Mono<Couple> getById(String id) {
        return coupleRepository.findById(id);
    }

    @Override
    public Mono<Void> create(Publisher<Couple> couplePublisher) {
        return coupleRepository.saveAll(couplePublisher).then();
    }

    @Override
    public Mono update(String id, Couple couple) {
        return coupleRepository.findById(id).flatMap(updatedCouple -> {
            updatedCouple.setPointLA(couple.getPointLA());
            updatedCouple.setPointST(couple.getPointST());
            updatedCouple.setBoostST(couple.getBoostST());
            updatedCouple.setBoostLA(couple.getBoostLA());
            return coupleRepository.save(updatedCouple);
        }).map(updatedCouple -> ResponseEntity.ok(updatedCouple)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono delete(String id) {
        final Mono<Couple> coupleMonoToBeDeleted = getById(id);
        if (Objects.isNull(coupleMonoToBeDeleted)) {
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(couple -> coupleRepository.delete(couple).then(Mono.just(couple)));
    }
}
