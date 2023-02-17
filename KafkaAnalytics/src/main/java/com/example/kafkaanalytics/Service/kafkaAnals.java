package com.example.kafkaanalytics.Service;

import com.example.kafkaanalytics.Model.Bill;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.Function;

@Service
public class kafkaAnals {

    @Bean
    public Function<KStream<String, Bill>,KStream<String,Long>>kStreamKStreamFunction ()
    {
        return (input)->{
            return input
                    .map((k,v)->new KeyValue<>(String.valueOf(v.getCustomer().getName()),0L))
                    .groupBy((k,v)->k, Grouped.with(Serdes.String(),Serdes.Long()))
                    .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                    .count(Materialized.as("page-count"))
                    .toStream()
                    .map((k,v)->new KeyValue<>(k.key(),v));
        };
    }
}
