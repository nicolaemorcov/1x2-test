package org.test.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public interface CustomMessageListener extends MessageListener {

    default Object getMessage(Object message){
        ConsumerRecord consumerRecord = (ConsumerRecord) message;
        return consumerRecord.value();
    }

}
