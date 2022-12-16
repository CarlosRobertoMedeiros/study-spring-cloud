package br.com.roberto.resilience.produto.rest;

import br.com.roberto.resilience.produto.client.AvaliacaoResponse;
import br.com.roberto.resilience.produto.client.FeignAvaliacaoClient;
import br.com.roberto.resilience.produto.domain.entity.Produto;
import br.com.roberto.resilience.produto.domain.repository.ProdutoRepository;
import br.com.roberto.resilience.produto.rest.dto.ProdutoResponse;
import br.com.roberto.resilience.produto.rest.exceptions.RecursoNaoEncontradoException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoRest {

    private final ProdutoRepository produtoRepository;
    private final FeignAvaliacaoClient avaliacaoClient;

    public ProdutoRest(ProdutoRepository produtoRepository, FeignAvaliacaoClient avaliacaoClient) {
        this.produtoRepository = produtoRepository;
        this.avaliacaoClient = avaliacaoClient;
    }

    @GetMapping
    public List<ProdutoResponse> buscarTodos() {
        return produtoRepository.getAll()
                .stream()
                .map(this::converterProdutoParaModelo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{produtoId}")
    public ProdutoResponse buscarPorId(@PathVariable Long produtoId) {
        return produtoRepository.getOne(produtoId)
                .map(this::converterProdutoParaModeloComAvaliacao)
                .orElseThrow(RecursoNaoEncontradoException::new);
    }

    private ProdutoResponse converterProdutoParaModelo(Produto produto) {
        return ProdutoResponse.of(produto,null);
    }

    private ProdutoResponse converterProdutoParaModeloComAvaliacao(Produto produto) {
        return ProdutoResponse.of(produto, buscarAvaliacaoDoProduto(produto.getId()));
    }

    private List<AvaliacaoResponse> buscarAvaliacaoDoProduto(Long produtoId) {
        return avaliacaoClient.buscarTodosPorProduto(produtoId);
    }




}
