package com.bcaf.solace.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

@SpringBootApplication
public class SolaceConsumerApp {

    private static final Logger logger = LoggerFactory.getLogger(SolaceConsumerApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SolaceConsumerApp.class);
    }

    //consumer-in-0
    @Bean
    public Consumer<Message<String>> consumer(){
        return (payload) -> {
            logger.info("Consume Payload : " + payload.getPayload());
        };
    }
}
