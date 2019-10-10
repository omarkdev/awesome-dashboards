package br.com.teste.domain.entity;


import javax.persistence.*;
import java.io.Serializable;


/**
 * Classe responsavel pelo objeto e tabela do banco
 */
@Entity
@Table(name = "jogos")
public class Jogos  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idJogos;
    @Column(name = "mandante")
    String mandante;
    @Column(name = "visitante")
    String visitante;
    @Column(name = "resultado")
    String resultado;

    public String getMandante() {
        return mandante;
    }

    public void setMandante(String mandante) {
        this.mandante = mandante;
    }

    public String getVisitante() {
        return visitante;
    }

    public void setVisitante(String visitante) {
        this.visitante = visitante;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }


}
