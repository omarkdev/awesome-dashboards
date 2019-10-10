package br.com.teste;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class Utils {

    private static String url = "https://globoesporte.globo.com/placar-ge/%s/jogos.ghtml";

    /**
     * Metódo responsavel pelo range de datas
     *
     * @return
     */
    public static List<String> data() {
        List<String> dataReturn = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LocalDate data = LocalDate.now();
            LocalDate dataAtual = data.plusDays(-i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedString = dataAtual.format(formatter);
            dataReturn.add(formattedString);
        }
        return dataReturn;
    }

    /**
     * Metodo responsavel por concatenar as urls da requisições
     *
     * @return
     */
    public List<String> requestInicial() {
        List<String> listData = new ArrayList<>();
        for (String ini : Utils.data()) {
            String url2 = String.format(url, ini);
            listData.add(url2);
        }
        return listData;

    }
}
