package org.practica.udemy.programacionractiva1.infrastructure.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/mono")
    public Mono<String> hello() {
        return Mono.just("Hello Desde Mono Reactivo");
    }

    @GetMapping("/flux")
    public Flux<String> helloFlux() {
        return Flux.just("Hello Flux Reactivo", "hola", "desde ","flux");
    }

    @GetMapping("/flux-delay")
    public Flux<String> fluxWithDelay() {
        return Flux.just("A", "B", "C")
                .delayElements(Duration.ofSeconds(1));
    }
}
