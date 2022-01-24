package org.test.kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import javax.annotation.PostConstruct;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class MessageEventsProducerConfig {

    private static final String MESSAGE_PRODUCERS_PROPERTIES = "message-producers.properties";

    @Value(value = "${spring.kafka.bootstrap-servers:#{null}}")
    private String bootstrapAddress;

    private static final Map<String, String> topicsAndMsgTypes = new HashMap<>();

    private static final Map<String, KafkaTemplate> kafkaTemplates = new HashMap<>();

    @PostConstruct
    public <T> void initializer() throws Exception {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        if (bootstrapAddress != null){
            Properties topics = new Properties();
            topics.load(this.getClass().getClassLoader().getResourceAsStream(MESSAGE_PRODUCERS_PROPERTIES));
            final Enumeration<Object> keys = topics.keys();
            while (keys.hasMoreElements()){
                 String key = (String) keys.nextElement();
                 String value = (String) topics.get(key);
                 topicsAndMsgTypes.put(key, value);
            }
            topicsAndMsgTypes.forEach((topic, type) -> {
                try {
                    Class messageType = Class.forName(type);
                    final DefaultKafkaProducerFactory<String, T> producerFactory =
                            new DefaultKafkaProducerFactory<>(configProps, new StringSerializer(),
                                    new JsonSerializer<>());
                    kafkaTemplates.put(topic, new KafkaTemplate(producerFactory));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public void send(String topicName, Object o){
        kafkaTemplates.get(topicName).send(topicName, o);}

}
