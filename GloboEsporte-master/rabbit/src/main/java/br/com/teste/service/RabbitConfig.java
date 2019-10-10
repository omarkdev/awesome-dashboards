package br.com.teste.service;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe de configuração e criação de filas do rabbit
 */
@Configuration
@EnableRabbit
public class RabbitConfig {

    @Value("${spring.rabbitmq-config.queues.queue-producer}")
    private String producerQueue;

    @Value("${spring.rabbitmq-config.queues.queue-consumer}")
    private String consumerQueue;


    @Bean(name = "producerQueue")
    @Primary
    public Queue queueProducer() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-max-priority", 10);
        return new Queue(producerQueue, true, false, false, map);
    }

    @Bean(name = "consumerQueue")
    @Primary
    public Queue queueConsumer() {
        Map<String, Object> map = new HashMap<>();
        map.put("max-priority", 10);
        return new Queue(consumerQueue, true, false, false, map);
    }

}
