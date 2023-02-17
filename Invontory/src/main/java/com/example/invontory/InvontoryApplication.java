package com.example.invontory;

import com.example.invontory.entity.Product;
import com.example.invontory.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InvontoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvontoryApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration) {
       restConfiguration.exposeIdsFor(Product.class);
        return args -> {

            productRepository.save(new Product(null, "Coca", 3.5, 2));
            productRepository.save(new Product(null, "King cookies", 2, 1));
            productRepository.save(new Product(null, "Raibi", 2.5, 3));
            productRepository.save(new Product(null, "Merendina", 2, 2));

            productRepository.findAll().forEach(p->{
                System.out.println(p.getId());
            });
        };
    }
}
