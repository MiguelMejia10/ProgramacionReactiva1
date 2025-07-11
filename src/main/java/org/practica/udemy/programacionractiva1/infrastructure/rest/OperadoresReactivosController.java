package org.practica.udemy.programacionractiva1.infrastructure.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/operador")
public class OperadoresReactivosController {

    @GetMapping("/flux-map")
    public Flux<String> fluxMap(){
        return Flux.just("Miguel", "Juan", "Ana")
                .map(String::toUpperCase)
                .delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("/flux-flatmap")
    public Flux<String> fluxFlatMap(){
        return Flux.just("A", "B", "C")
                .flatMap(letra ->
                        Mono.just(letra+ "!")
                                .delayElement(Duration.ofMillis(500))
                );
    }

    @GetMapping("/flux-filter")
    public Flux<String> fluxFilter(){
        return Flux.just("uno", "dos", "tres", "cuatro")
                .filter(palabra -> palabra.length()>3);
    }

    @GetMapping("/flux-zip")
    public Flux<String> fluxZip(){
        Flux<String> nombres = Flux.just("Carlos", "Laura");
        Flux<String> apellidos = Flux.just("Mejia", "Lozano");

        return Flux.zip(nombres,apellidos, (nombre,apellido) -> nombre + " " + apellido );
    }

    public Flux<String> fluxMerge(){
        Flux<String> flujo1 = Flux.just("A", "B").delayElements(Duration.ofMillis(300));
        Flux<String> flujo2 = Flux.just("1", "2").delayElements(Duration.ofMillis(200));
        return Flux.merge(flujo1, flujo2);
    }
}
