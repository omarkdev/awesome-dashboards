package br.com.teste.crawler;


import br.com.teste.domain.entity.Jogos;
import br.com.teste.factory.NavegadorFactory;
import com.gargoylesoftware.htmlunit.html.HtmlArticle;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class PaginaHome {

    private final Logger logger = LoggerFactory.getLogger(PaginaHome.class);

    private final static String CAMPEONATO = ".//section[contains(., 'Campeonato') and not(contains(@class, 'secao-horario'))]//article";
    private final static String VISITANTE = ".//div[@itemprop='awayTeam']";
    private final static String MANDANTE = ".//div[@itemprop='homeTeam']";
    private final static String RESULTADO = ".//div[@class='resultado small-8 medium-4']";

    /**
     * Metodo responsavel pela captura dos dados no site GloboEsporte
     *
     * @param listJogos
     * @param url
     * @throws IOException
     */
    @Scheduled(cron = "0 30 8 1/1 * ? *", zone = "America/Sao_Paulo")
    public void buscaJogo(Set<Jogos> listJogos, String url) throws IOException {
        logger.info("DATA DE CONSULTA: " + regex(url));

        if (Objects.nonNull(url)) {
            AtomicInteger i = new AtomicInteger();
            HtmlPage page = NavegadorFactory.getWebClient().getPage("https://globoesporte.globo.com/placar-ge/10-04-2019/jogos.ghtml");


            List<HtmlArticle> divs = page.getByXPath(CAMPEONATO);

            divs.forEach(
                    t
                            -> {
                        Jogos jogo = new Jogos();
                        jogo.setMandante(t.<HtmlDivision>getFirstByXPath(MANDANTE).getTextContent());
                        jogo.setVisitante(t.<HtmlDivision>getFirstByXPath(VISITANTE).getTextContent());
                        jogo.setResultado(t.<HtmlDivision>getFirstByXPath(RESULTADO).getTextContent());
                        logger.info(i + " Jogo: " + jogo.getMandante() + " " + jogo.getResultado() + " " + jogo.getVisitante());
                        listJogos.add(jogo);
                    });


        } else {
            logger.warn("OBJETO NULL POR FAVOR VERIFICAR");
        }

    }

    public String regex(String full) {
        Pattern pattern = Pattern.compile("((\\d{2})-(\\d{2})-(\\d{4}))");
        Matcher matcher = pattern.matcher(full);
        if (matcher.find())
            matcher.group();
        return (matcher.group());
    }
}
