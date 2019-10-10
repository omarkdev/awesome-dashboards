package br.com.teste.service;


import br.com.teste.domain.entity.Jogos;
import br.com.teste.domain.repository.RepositoryJogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogosService {

    //TODO -> NOMECLATURA DOS PACOTES.

    @Autowired
    RepositoryJogo repositoryJogo;


    public Jogos saveJogo(Jogos obj) {
        return repositoryJogo.save(obj);
    }
}
