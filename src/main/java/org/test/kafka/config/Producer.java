package org.test.kafka.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final MessageEventsProducerConfig messageEventsProducerConfig;

    @Autowired
    public Producer(MessageEventsProducerConfig messageEventsProducerConfig) {
        this.messageEventsProducerConfig = messageEventsProducerConfig;
    }


    public void send(String topicName, Object object) {
        messageEventsProducerConfig.send(topicName, object);
    }

}
