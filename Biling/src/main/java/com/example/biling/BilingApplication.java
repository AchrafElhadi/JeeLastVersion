package com.example.biling;

import com.example.biling.Model.Customer;
import com.example.biling.Model.Product;
import com.example.biling.Repositories.BillRepository;
import com.example.biling.Repositories.ProductItemsRepository;
import com.example.biling.entity.Bill;
import com.example.biling.entity.ProductItems;
import com.example.biling.feign.CustomerRestClient;
import com.example.biling.feign.ProductItemRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.List;

@EnableFeignClients
@SpringBootApplication
public class BilingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilingApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            BillRepository billRepository,
            ProductItemsRepository productItemsRepository,
            CustomerRestClient customerRestClient,
            ProductItemRestClient productItemRestClient
    )
    {
        return  args -> {
            Customer customer=customerRestClient.getCustomerById(1L);
            Bill bill=new Bill(null,new Date(),null,customer.getId(),null);
            Bill bill2= billRepository.save(bill);
            Bill bill3=new Bill(null,new Date(),null,customer.getId(),null);
            Bill bill4= billRepository.save(bill3);
            PagedModel<Product> p=productItemRestClient.getProducts(0,6);
            p.forEach(v->{
                ProductItems pit=new ProductItems();
                pit.setPrice(v.getPrice());
                pit.setQuantity(Math.random()*10);
                pit.setProductID(v.getId());
                pit.setBill(bill2);
                productItemsRepository.save(pit);
            });
            p.forEach(v->{
                ProductItems pit=new ProductItems();
                pit.setPrice(v.getPrice());
                pit.setQuantity(Math.random()*10);
                pit.setProductID(v.getId());
                pit.setBill(bill4);
                productItemsRepository.save(pit);
            });
        };
    }
}
