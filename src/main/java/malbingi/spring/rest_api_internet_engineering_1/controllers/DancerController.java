package malbingi.spring.rest_api_internet_engineering_1.controllers;

import malbingi.spring.rest_api_internet_engineering_1.domain.Dancer;
import malbingi.spring.rest_api_internet_engineering_1.services.DancerService;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class DancerController {

    private final DancerService dancerService;

    public DancerController(DancerService dancerService) {
        this.dancerService = dancerService;
    }

    @GetMapping("/dancers")
    Flux<Dancer> list() {
        return dancerService.findAll();
    }

    @GetMapping("/dancers/{id}")
    Mono<Dancer> getById(@PathVariable String id) {
        return dancerService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/dancers")
    Mono<Void> create(@RequestBody Publisher<Dancer> dancerPublisher) {
        return dancerService.create(dancerPublisher);
    }

    @PutMapping("/dancers/{id}")
    Mono update(@PathVariable String id, @RequestBody Dancer dancer) {
        return dancerService.update(id, dancer);
    }

    @DeleteMapping("/dancers/{id}")
    Mono delete(@PathVariable String id) {
        return dancerService.delete(id);
    }
}
