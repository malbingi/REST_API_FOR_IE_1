package malbingi.spring.rest_api_internet_engineering_1.services;

import malbingi.spring.rest_api_internet_engineering_1.domain.Competition;
import malbingi.spring.rest_api_internet_engineering_1.repositories.CompetitionRepository;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Flux<Competition> findAll() {
        return competitionRepository.findAll();
    }

    @Override
    public Mono<Competition> getById(String id) {
        return competitionRepository.findById(id);
    }

    @Override
    public Mono<Void> create(Publisher<Competition> competitionPublisher) {
        return competitionRepository.saveAll(competitionPublisher).then();
    }

    @Override
    public Mono update(String id, Competition competition) {
        return competitionRepository.findById(id).flatMap(updatedCompetition -> {
            updatedCompetition.setName(competition.getName());
            updatedCompetition.setLocation(competition.getLocation());
            updatedCompetition.setLocalDateTime(competition.getLocalDateTime());
            return competitionRepository.save(updatedCompetition);
        }).map(updatedCompetition -> ResponseEntity.ok(updatedCompetition)).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public Mono delete(String id) {
        final Mono<Competition> competitionMonoToBeDeleted = getById(id);
        if (Objects.isNull(competitionMonoToBeDeleted)) {
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(competition -> competitionRepository.delete(competition).then(Mono.just(competition)));
    }
}
