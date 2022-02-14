package com.poc.bcaf.solace.messaging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.poc.bcaf.solace.messaging.model.SendEmail;
import com.poc.bcaf.solace.messaging.util.UtilBase64Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.lang.invoke.MethodHandles;


@Service
public class ProducerService {
    private final JmsTemplate jmsTemplate;
    private final JmsTemplate topicJmsTemplate;

    @Value("${solace.queue}")
    private String queueName;

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

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());


    public ProducerService(@Qualifier("jmsTemplate") JmsTemplate jmsTemplate,
                           @Qualifier("topicJmsTemplate") JmsTemplate topicJmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.topicJmsTemplate=topicJmsTemplate;
    }

    @PostConstruct
    public void init() throws JsonProcessingException {

//        String path = "D:\\SevenThirtyAttributeReport_2021-12-23.pdf";
//        String AttachmentBody = UtilBase64Image.encoder(path);

        SendEmail sendEmail = new SendEmail(From,To,Cc,Subject,Body,AttachmentName,AttachmentDescription,AttachmentType,AttachmentBody);
        String msg = sendEmail.toJSON();

        System.out.println("=== JSON PAYLOAD ===" + msg);

        this.jmsTemplate.send(queueName, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }


}
