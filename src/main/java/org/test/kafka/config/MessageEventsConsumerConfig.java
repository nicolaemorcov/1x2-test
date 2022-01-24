package org.test.kafka.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class MessageEventsConsumerConfig {


    @Value(value = "${spring.kafka.bootstrap-servers:#{null}}")
    private String bootstrapAddress;

    private static final AtomicLong SEQUENCE = new AtomicLong(new Random().nextInt() + System.nanoTime());

    public <T> void register(String topicName, Class<T> messageType, String groupId, CustomMessageListener messageListener){
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        if (bootstrapAddress != null){
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule())
                    .registerModule(new Jdk8Module()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ContainerProperties containerProperties = new ContainerProperties(topicName);
            containerProperties.setMessageListener(messageListener);
            final DefaultKafkaConsumerFactory<String, T> defaultConsumerFactory =
                    new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(),
                            new JsonDeserializer<>(messageType, objectMapper));
            final ConcurrentMessageListenerContainer container =
                    new ConcurrentMessageListenerContainer(defaultConsumerFactory, containerProperties);
            container.start();
        }
    }

}
