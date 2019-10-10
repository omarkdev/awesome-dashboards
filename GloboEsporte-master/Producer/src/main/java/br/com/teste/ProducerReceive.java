package br.com.teste;


import br.com.teste.domain.entity.Jogos;
import br.com.teste.service.JogosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProducerReceive {

    private final Logger logger = LoggerFactory.getLogger(ProducerReceive.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq-config.queues.queue-consumer}")
    private String queueConsumer;

    @Autowired
    JogosService jogosService;


    @RabbitListener(queues = {"${spring.rabbitmq-config.queues.queue-consumer}"})
    public void receiveProducer(@Payload Set<Jogos> listJogos) throws Exception {

        logger.info("---------------------------------------------------");
        logger.info("RECEBENDO MENSAGEM RABBIT");
        for (Jogos jogo : listJogos) {
            System.out.println(jogo.getMandante());
            System.out.println(jogo.getVisitante());
            System.out.println(jogo.getResultado());
            jogosService.saveJogo(jogo);
        }

    }
}
