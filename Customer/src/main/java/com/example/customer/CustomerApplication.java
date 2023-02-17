package com.example.customer;

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration)
    {
        restConfiguration.exposeIdsFor(Customer.class);
        return args->{

            customerRepository.save(new Customer(null,"achraf","elhadi"));
            customerRepository.save(new Customer(null,"adam","elhadi"));
            customerRepository.save(new Customer(null,"Kettane","Mohamed"));
            customerRepository.save(new Customer(null,"ismail","mounir"));
        };
    }
}
