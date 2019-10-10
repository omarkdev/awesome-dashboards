package br.com.teste;


import br.com.teste.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;



@Service
public class ProducerService {

    private final Logger logger = LoggerFactory.getLogger(Controller.class);


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq-config.queues.queue-producer}")
    private String queueProducer;

    /**
     * Metodo para envio de pendencias para as filas do rabbit
     * @param listUrl
     */

    @Scheduled(cron = "0 30 8 1/1 * ? *", zone = "America/Sao_Paulo")

    public void enviaParaFila(List<String> listUrl) {
        for (String url : listUrl) {

            if (Objects.nonNull(url)) {
                rabbitTemplate.convertAndSend("",queueProducer,url);
                logger.info("ENVIADO PARA FILA COM SUCESSO");
            } else {
                logger.warn("ERRO AO ENVIAR PARA FILA");
            }
        }

    }

}


