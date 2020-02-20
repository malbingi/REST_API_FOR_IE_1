package malbingi.spring.rest_api_internet_engineering_1.controllers;

import malbingi.spring.rest_api_internet_engineering_1.domain.Couple;
import malbingi.spring.rest_api_internet_engineering_1.services.CoupleService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CoupleController {

    private final CoupleService coupleService;

    public CoupleController(CoupleService coupleService) {
        this.coupleService = coupleService;
    }

    @GetMapping("/couples")
    Flux<Couple> list() {
        return coupleService.findAll();
    }

    @GetMapping("/couples/{id}")
    Mono<Couple> getById(@PathVariable String id) {
        return coupleService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/couples")
    Mono<Void> create(@RequestBody Publisher<Couple> couplePublisher) {
        return coupleService.create(couplePublisher);
    }

    @PutMapping("/couples/{id}")
    Mono update(@PathVariable String id, @RequestBody Couple couple) {
        return coupleService.update(id, couple);
    }

    @DeleteMapping("/couples/{id}")
    Mono delete(@PathVariable String id) {
        return coupleService.delete(id);
    }
}
