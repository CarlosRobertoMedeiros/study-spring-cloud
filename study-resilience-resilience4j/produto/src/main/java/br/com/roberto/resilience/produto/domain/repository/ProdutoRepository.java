package br.com.roberto.resilience.produto.domain.repository;

import br.com.roberto.resilience.produto.domain.entity.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    void save(Produto produto);
    Optional<Produto> getOne(Long id);
    List<Produto> getAll();
}
