package malbingi.spring.rest_api_internet_engineering_1.controllers;

import malbingi.spring.rest_api_internet_engineering_1.domain.Competition;
import malbingi.spring.rest_api_internet_engineering_1.services.CompetitionService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CompetitionController {

    private final CompetitionService competitionService;

    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/competitions")
    Flux<Competition> list() {
        return competitionService.findAll();
    }

    @GetMapping("/competitions/{id}")
    Mono<Competition> getById(@PathVariable String id) {
        return competitionService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/competitions")
    Mono<Void> create(@RequestBody Publisher<Competition> competitionPublisher) {
        return competitionService.create(competitionPublisher);
    }

    @PutMapping("/competitions/{id}")
    Mono update(@PathVariable String id, @RequestBody Competition competition) {
        return competitionService.update(id, competition);
    }

    @DeleteMapping("/competitions/{id}")
    Mono delete(@PathVariable String id) {
        return competitionService.delete(id);
    }
}
