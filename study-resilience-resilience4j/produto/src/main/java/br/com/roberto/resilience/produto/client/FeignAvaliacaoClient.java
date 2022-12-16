package br.com.roberto.resilience.produto.client;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ServiceAvaliacao",url = "http://localhost:8091/avaliacoes/")
public interface FeignAvaliacaoClient {

    @CircuitBreaker(name = "avaliacoesCB")
    @Retry(name = "avaliacoesCB")
    @RequestMapping(method = RequestMethod.GET)
    List<AvaliacaoResponse> buscarTodosPorProduto(@RequestParam("produtoId") Long produtoId);
}
