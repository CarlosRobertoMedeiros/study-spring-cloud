package br.com.roberto.resilience.produto.infra.client;

import br.com.roberto.resilience.produto.client.AvaliacaoClient;
import br.com.roberto.resilience.produto.client.AvaliacaoResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class AvaliacaoClientImpl implements AvaliacaoClient {

    private final Logger logger = LoggerFactory.getLogger(AvaliacaoClientImpl.class);
    private final RestTemplate restTemplate;
    private static final  String API_URL = UriComponentsBuilder
            .fromHttpUrl("http://localhost:8091/avaliacoes")
            .queryParam("produtoId", "{produtoId}")
            .encode()
            .toUriString();
    private final Map<Long, List<AvaliacaoResponse>> CACHE_AVALIACOES = new HashMap<>();

    private AtomicInteger acum = new AtomicInteger(1);
    public AvaliacaoClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @CircuitBreaker(name="avaliacoesCB" /*,fallbackMethod = "buscarTodosPorProdutoNoCacheFallback"*/)
    @Retry(name = "avaliacoesCB", fallbackMethod = "buscarTodosPorProdutoNoCacheFallback")
    public List<AvaliacaoResponse> buscarTodosPorProduto(Long productId) {
        logger.info(String.valueOf("Retry"+acum.getAndIncrement()));
        final List<AvaliacaoResponse> avaliacoes = executarRequisicao(productId);
        return avaliacoes;
    }

    private List<AvaliacaoResponse> executarRequisicao(Long produtoId) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("produtoId", produtoId);

        logger.info("Buscando avaliações");
        final AvaliacaoResponse[] avaliacoes;

        try {
            avaliacoes = restTemplate.getForObject(API_URL, AvaliacaoResponse[].class, parametros);
        } catch (Exception e) {
            logger.error("Erro ao buscar avaliações");
            throw (e);
        }

        logger.info("Alimentando o Cache");
        CACHE_AVALIACOES.put(produtoId, Arrays.asList(avaliacoes));
        return Arrays.asList(avaliacoes);
    }

    private List<AvaliacaoResponse> buscarTodosPorProdutoNoCacheFallback(Long produtoId, Throwable t){
        logger.info("Buscando no Cache");
        return CACHE_AVALIACOES.getOrDefault(produtoId, new ArrayList<>());
    }

}
