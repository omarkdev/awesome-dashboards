package br.com.teste.controller;


import br.com.teste.ProducerReceive;
import br.com.teste.ProducerService;
import br.com.teste.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;


@EnableScheduling
@Component
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    /**
     * TODO -> DIVIDIR O PROJETO EM MODULOS PARA MELHOR ORGANIZA-LO.
     * TODO -> TENTAR IMPLEMENTAR RABBIT PARA MELHOR CONTROLE DE THREADS, E DEIXAR A SOLUÇÃO MAIS PERFORMATICA E ESCALAVEL.
     * TODO -> SE POSSIVEL, JÁ CRIA O DOCKER FILE, PARA UM POSSIVEL DEPLOY AUTOMATICO, (JA DEIXA CRIADO O DOCKER FILE).
     * TODO -> NO PADRAO DE PACOTES, COM RABBIT, TENTA COLOCAR PARA QUE O PRODUCER ENVIE UM RANGE DE DATAS, E OS CONSUMERS PESQUISEM
     * OS JOGOS DESSE RANGE.
     */
    //TODO -> Mudar o nome do pacote, para conter apenas letras minusculas.


    @Autowired
    Utils utils;

    @Autowired
    ProducerService queueProducerService;

    @Scheduled(cron = "0 0 8 1/1 * ? *", zone = "America/Sao_Paulo")
    public void inicio() throws IOException {

        logger.info("INICIANDO ROBO DE CAPTURA\n");
        List<String> listURL = utils.requestInicial();
        queueProducerService.enviaParaFila(listURL);

        logger.info("FINALIZANDO ROBO");

    }



}
