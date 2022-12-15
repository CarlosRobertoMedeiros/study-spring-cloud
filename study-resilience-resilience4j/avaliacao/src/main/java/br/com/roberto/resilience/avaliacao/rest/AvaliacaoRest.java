package br.com.roberto.resilience.avaliacao.rest;


import br.com.roberto.resilience.avaliacao.domain.repository.AvaliacaoRepository;
import br.com.roberto.resilience.avaliacao.rest.dto.AvaliacaoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoRest {

    private final AvaliacaoRepository avaliacaos;

    public AvaliacaoRest(AvaliacaoRepository avaliacaos) {
        this.avaliacaos = avaliacaos;
    }

    @GetMapping
    public List<AvaliacaoResponse> buscarPorProduto(@RequestParam Long produtoId) {
        return avaliacaos.getAll()
                .stream()
                .filter(avaliacao -> avaliacao.getProdutoId().equals(produtoId))
                .map(AvaliacaoResponse::of)
                .collect(Collectors.toList());
    }

}
