package com.bcaf.solace.messaging;

import com.bcaf.solace.model.SendEmail;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class SolaceMessaging {

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

    private static final Logger logger = LoggerFactory.getLogger(SolaceMessaging.class);

    public static void main(String[] args) {
        SpringApplication.run(SolaceMessaging.class, args);
    }

    //producer-out-0
//    @Bean
//    public Supplier<Flux<Long>> producer(){
//        return () -> Flux.interval(Duration.ofSeconds(1))
//                .log();
//    }

    //processor-in-0
    //processor-out-0
    @Bean
    public Function<String, String> processor(){
        return msg -> {
            SendEmail sendEmail = new SendEmail(From,
                    To,
                    Cc,
                    Subject,
                    Body,
                    AttachmentName,
                    AttachmentDescription,
                    AttachmentType,
                    AttachmentBody);;
            try {
                msg = sendEmail.toJSON();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            logger.info("Construct JSON : " + msg);
            return msg;
        };
    }

    //consumer-in-0
//    @Bean
//    public Consumer<Message<String>> consumer(){
//        return (payload) -> {
//            logger.info("Consume Payload : " + payload.getPayload());
//        };
//    }
}
