package com.example.biling.feign;

import com.example.biling.Model.Customer;
import com.example.biling.Model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductItemRestClient {
    @GetMapping(path = "/products")
    PagedModel<Product> getProducts(@RequestParam(name="page",defaultValue = "") int page, @RequestParam(name="size") int size);
    @GetMapping(path="/products/{id}")
    Product getProductById(@PathVariable Long id);
}
