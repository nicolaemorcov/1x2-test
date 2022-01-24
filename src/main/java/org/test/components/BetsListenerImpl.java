package org.test.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.test.dto.BetRaw;
import org.test.kafka.config.CustomMessageListener;
import org.test.kafka.config.MessageEventsConsumerConfig;

import javax.annotation.PostConstruct;

@Component
public class BetsListenerImpl implements CustomMessageListener {

    private static final String TOPIC = "bet_detail";

    private static final String GROUP_ID = "my_group_id";


    MessageEventsConsumerConfig messageEventsConsumerConfig;

    @Autowired
    public BetsListenerImpl(MessageEventsConsumerConfig messageEventsConsumerConfig) {
        this.messageEventsConsumerConfig = messageEventsConsumerConfig;
    }

    @PostConstruct
    public void initializer(){
        messageEventsConsumerConfig.register(TOPIC, BetRaw.class, GROUP_ID, this);
    }

    @Override
    public void onMessage(Object o) {
        BetRaw betRaw = (BetRaw) getMessage(o);
        if (betRaw.getReturns() >= 1500){
            System.out.println("The RETURN is 1500.00 or greater !!! Bet=" + betRaw.toString());
        }
    }
}
