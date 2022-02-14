package com.poc.bcaf.solace.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.Iterator;

@Service
public class ConsumerListener {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @JmsListener(containerFactory="queueListenerContainerFactory", destination = "${solace.queue}")
    public void listenToQueue1(Message message){
    	logger.info("Listener queue-1 called {}",message);
        StringBuffer msgAsStr = new StringBuffer("============= Received \nHeaders:");
        MessageHeaders hdrs = message.getHeaders();
        msgAsStr.append("\nUUID: "+hdrs.getId());
        msgAsStr.append("\nTimestamp: "+hdrs.getTimestamp());
        Iterator<String> keyIter = hdrs.keySet().iterator();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            msgAsStr.append("\n"+key+": "+hdrs.get(key));
        }
        msgAsStr.append("\nPayload: "+message.getPayload());
        logger.info(msgAsStr.toString());
    }

}
