package com.bcaf.solace.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Supplier;

@SpringBootApplication
public class SolaceProducerApp {

    private static final Logger logger = LoggerFactory.getLogger(SolaceProducerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SolaceProducerApp.class);
    }

    //producer-out-0
    @Bean
    public Supplier<String> producer(){
        return () -> {
            String msg = "Hallo BCAF";
            logger.info(msg);
            return msg;
        };
    }

}
