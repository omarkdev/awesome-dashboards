package br.com.teste.service;


import br.com.teste.domain.entity.Jogos;
import br.com.teste.crawler.PaginaHome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



@Component
public class ConsumerService {

    @Autowired
    private PaginaHome paginaHome;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq-config.queues.queue-consumer}")
    private String queueConsumer;

    Set<Jogos> listJogos = new HashSet<>();

    private final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @RabbitListener(queues = {"${spring.rabbitmq-config.queues.queue-producer}"})
    public void receive(@Payload String body) throws Exception{

        logger.info("---------------------------------------------------");
        logger.info("RECEBENDO MENSAGEM RABBIT");
        String dataProducer = body;

        paginaHome.buscaJogo(listJogos, body);
        enviaFilaConsumer(listJogos);
    }


    /**
     * metodo de conversão objeto e envio pra fila
     * @param listJogos
     */
    public void enviaFilaConsumer(Set<Jogos> listJogos ){
        logger.info("---------------------------------------------------");
        logger.info("ENVIANDO CASOS PARA FILA CONSUMER");
        logger.info("QUANTIDADE DE CASOS PARA ENVIAR "+ listJogos.size());

        if(!listJogos.isEmpty()){
            rabbitTemplate.convertAndSend("",queueConsumer,listJogos);
            logger.info("CASOS INSERIDOS COM SUCESSO");

        }else {
            logger.info("ERRO AO ENVIAR A FILA DE CONSUMER");
        };

    }
}
