package org.practica.udemy.programacionractiva1.adapter.web;


import org.practica.udemy.programacionractiva1.domain.model.Product;
import org.practica.udemy.programacionractiva1.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/products")
public class ProductViewController {

    @Autowired
    private ProductService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.findAll());
        return "products";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping
    public Mono<String> save(@ModelAttribute Product product) {
        return service.save(product)
                .thenReturn("redirect:/products");
    }
}
