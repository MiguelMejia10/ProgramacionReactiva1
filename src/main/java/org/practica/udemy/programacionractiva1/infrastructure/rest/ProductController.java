package org.practica.udemy.programacionractiva1.infrastructure.rest;

import org.practica.udemy.programacionractiva1.domain.model.Product;
import org.practica.udemy.programacionractiva1.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public Mono<Product> create(@RequestBody Product p) {
        return service.save(p);
    }

    @GetMapping
    public Flux<Product> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> update(@PathVariable String id, @RequestBody Product updated) {
        return service.findById(id)
                .flatMap(existing -> {
                    existing.setName(updated.getName());
                    existing.setPrice(updated.getPrice());
                    return service.save(existing);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.deleteById(id);
    }

    @GetMapping("/search")
    public Flux<Product> search(@RequestParam String name) {
        return service.searchByName(name);
    }
}


