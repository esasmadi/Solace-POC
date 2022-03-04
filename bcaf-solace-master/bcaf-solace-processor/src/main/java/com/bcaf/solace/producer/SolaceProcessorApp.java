package com.bcaf.solace.processor;

import com.bcaf.solace.producer.model.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SolaceProcessorApp {

    @Value("${notif.from}")
    private String From;
    @Value("${notif.to}")
    private String To;
    @Value("${notif.cc}")
    private String Cc;
    @Value("${notif.subject}")
    private String Subject;
    private String Body;
    private String AttachmentName;
    private String AttachmentDescription;
    private String AttachmentType;
    private String AttachmentBody;

    private static final Logger logger = LoggerFactory.getLogger(SolaceProcessorApp.class);

    public static void main(String[] args) {
        SpringApplication.run(SolaceProcessorApp.class);
    }

    //processor-in-0
    //processor-out-0
    @Bean
    public Function<String, String> processor(){
        return msg -> {
            Notification sendNotification = new Notification(From,
                    To,
                    Cc,
                    Subject,
                    Body,
                    AttachmentName,
                    AttachmentDescription,
                    AttachmentType,
                    AttachmentBody);;
            try {
                msg = sendNotification.toJSON();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            logger.info("Construct JSON : " + msg);
            return msg;
        };
    }
}
