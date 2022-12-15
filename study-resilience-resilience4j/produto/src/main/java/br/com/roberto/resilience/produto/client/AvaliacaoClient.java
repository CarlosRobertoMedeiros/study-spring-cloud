package br.com.roberto.resilience.produto.client;

import java.util.List;

public interface AvaliacaoClient {
    List<AvaliacaoResponse> buscarTodosPorProduto(Long productId);
}
