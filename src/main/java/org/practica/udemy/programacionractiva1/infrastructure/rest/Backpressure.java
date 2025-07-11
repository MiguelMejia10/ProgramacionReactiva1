package org.practica.udemy.programacionractiva1.infrastructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/contrapresion")
public class Backpressure {

    @GetMapping("/flux-limit")
    public Flux<Integer> fluxWithLimitRate(){
        return Flux.range(1, 20)
                .log()
                .limitRate(5); // Se Solicita 5 elementos a la vez
    }

    @GetMapping("/flux-buffer")
    public Flux<Integer> fluxWithBufffer(){
        return Flux.range(1, 100)
                .onBackpressureBuffer()
                .delayElements(Duration.ofMillis(50));
    }

    @GetMapping(".flux-drop")
    public Flux<Integer> fluxWithDrop(){
        return Flux.range(1, 100)
                .onBackpressureDrop()
                .delayElements(Duration.ofMillis(50));
    }

    @GetMapping("/flux-latest")
    public Flux<Integer> fluxWithLatest(){
        return Flux.range(1, 100)
                .onBackpressureLatest()
                .delayElements(Duration.ofMillis(50));
    }

}
