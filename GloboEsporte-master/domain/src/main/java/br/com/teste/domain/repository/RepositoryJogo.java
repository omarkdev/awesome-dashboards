package br.com.teste.domain.repository;

import br.com.teste.domain.entity.Jogos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryJogo extends JpaRepository<Jogos, Long> {


}
