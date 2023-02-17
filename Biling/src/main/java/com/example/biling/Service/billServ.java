package com.example.biling.Service;


import com.example.biling.Repositories.BillRepository;
import com.example.biling.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class billServ {

    @Autowired
    BillRepository billRepository;
    @Bean
    public Consumer<Bill>billConsumer()
    {
        return (input)->{
            System.out.println("++++++++++++++++++++");
            System.out.println(input.toString());
            billRepository.save(input);
            System.out.println("______________+++++++++__________");
        };
    }
}
