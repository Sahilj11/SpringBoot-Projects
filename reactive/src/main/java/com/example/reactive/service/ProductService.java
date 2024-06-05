package com.example.reactive.service;

import com.example.reactive.Product;
import java.time.Duration;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/** ProductService */
@Service
public class ProductService {

    public Flux<Product> getAll() {
        var p1 = new Product("Cock");
        var p2 = new Product("Snaks");
        Flux<Product> fl = Flux.fromStream(List.of(p1, p2).stream()).delayElements(Duration.ofSeconds(3));
        return fl;
    }
}
