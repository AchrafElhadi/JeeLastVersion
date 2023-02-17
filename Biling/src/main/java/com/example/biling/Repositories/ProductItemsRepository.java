package com.example.biling.Repositories;

import com.example.biling.entity.Bill;
import com.example.biling.entity.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductItemsRepository extends JpaRepository<ProductItems,Long> {
    public List<ProductItems> findByBillId(Long id);

}

