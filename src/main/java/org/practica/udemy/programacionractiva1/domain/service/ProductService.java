package org.practica.udemy.programacionractiva1.domain.service;

import org.practica.udemy.programacionractiva1.domain.model.Product;
import org.practica.udemy.programacionractiva1.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {


        @Autowired
        private ProductRepository repository;

        public Mono<Product> save(Product p) {
            return repository.save(p);
        }

        public Flux<Product> findAll() {
            return repository.findAll();
        }

        public Mono<Product> findById(String id) {
            return repository.findById(id);
        }

        public Mono<Void> deleteById(String id) {
            return repository.deleteById(id);
        }

        public Flux<Product> searchByName(String name) {
            return repository.findByNameContainingIgnoreCase(name);
        }
    }
