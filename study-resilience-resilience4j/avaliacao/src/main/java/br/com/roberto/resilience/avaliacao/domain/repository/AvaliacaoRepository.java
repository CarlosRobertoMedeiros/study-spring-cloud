package br.com.roberto.resilience.avaliacao.domain.repository;

import br.com.roberto.resilience.avaliacao.domain.entity.Avaliacao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository {

    void save(Avaliacao avaliacao);
    Optional<Avaliacao> getOne(Long id);
    List<Avaliacao> getAll();
}
