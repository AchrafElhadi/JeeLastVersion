package com.example.kafka.services;

import com.example.kafka.entities.Bill;
import com.example.kafka.entities.PageEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
public class PageEventService {
    @Bean
    public Consumer<PageEvent>pageEventConsumer()
    {
        return (input)->{
            System.out.println("**************");
            System.out.println(input.toString());
            System.out.println("**************");
        };
    }

    @Bean
    public Supplier<PageEvent>pageEventSupplier()
    {
        return ()->new PageEvent(Math.random()>0.5?"p1":"p2",Math.random()>0.5?"u1":"u2",new Date(),new Random().nextInt(9000));
    }

    @Bean
    public Function<PageEvent,PageEvent>pageEventFunction()
    {
        return (input)-> {
            input.setName("Page Event");
            input.setUser("Usser");

            return input;
        };
    }

//    @Bean
//    public Function<KStream<String,PageEvent>,KStream<String,Long>>kStreamKStreamFunction ()
//    {
//        return (input)->{
//            return input
//                    .filter((k,v)->v.getDuration()>100)
//                    .map((k,v)->new KeyValue<>(v.getName(),0L))
//                    .groupBy((k,v)->k, Grouped.with(Serdes.String(),Serdes.Long()))
//                    .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
//                    .count(Materialized.as("page-count"))
//                    .toStream()
//                    .map((k,v)->new KeyValue<>("=>"+k.window().startTime()+" "+k.window().endTime()+" "+k.key(),v));
//        };
//    }
@Bean
    public Function<KStream<String, Bill>,KStream<String,Long>>kStreamKStreamFunction ()
    {
        return (input)->{
            return input
                    .map((k,v)->new KeyValue<>(String.valueOf(v.getCustomer().getName()),"0L"))
                    .groupBy((k,v)->k, Grouped.with(Serdes.String(),Serdes.String()))
                    .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                    .count(Materialized.as("page-count"))
                    .toStream()
                    .map((k,v)->new KeyValue<>(k.key(),v));
        };
    }
//@Bean
//public Function<KStream<String , Customer>,KStream<String,Long>>kStreamKStreamFunction ()
//{
//    return (input)->{
//        return input
//                .map((k,v)->new KeyValue<>(v.getName(),0L))
//                .groupBy((k,v)->k, Grouped.with(Serdes.String(),Serdes.Long()))
//                .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
//                .count(Materialized.as("page-count"))
//                .toStream()
//                .map((k,v)->new KeyValue<>(k.key(),v));
//    };
//}
}
