package org.practica.udemy.programacionractiva1.domain.repository;

import org.practica.udemy.programacionractiva1.domain.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findByNameContainingIgnoreCase(String name);
}
