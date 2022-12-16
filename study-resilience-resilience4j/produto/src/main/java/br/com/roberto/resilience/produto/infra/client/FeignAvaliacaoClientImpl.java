package br.com.roberto.resilience.produto.infra.client;

import br.com.roberto.resilience.produto.client.AvaliacaoResponse;
import br.com.roberto.resilience.produto.client.FeignAvaliacaoClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


@Component
public class FeignAvaliacaoClientImpl implements FeignAvaliacaoClient {

    private final Logger logger = LoggerFactory.getLogger(FeignAvaliacaoClientImpl.class);
    private final FeignAvaliacaoClient feignAvaliacaoClient;

    private final Map<Long, List<AvaliacaoResponse>> CACHE_AVALIACOES = new HashMap<>();

    private AtomicInteger acum = new AtomicInteger(1);

    public FeignAvaliacaoClientImpl(FeignAvaliacaoClient feignAvaliacaoClient) {
        this.feignAvaliacaoClient = feignAvaliacaoClient;
    }
    @Override
    public List<AvaliacaoResponse> buscarTodosPorProduto(Long produtoId) {
        logger.info(String.valueOf("Retry"+acum.getAndIncrement()));
        final List<AvaliacaoResponse> avaliacoes = executarRequisicao(produtoId);
        return avaliacoes;
    }

    private List<AvaliacaoResponse> executarRequisicao(Long produtoId) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("produtoId", produtoId);

        logger.info("Buscando avaliações");
        final List<AvaliacaoResponse> avaliacoes;

        try {
            avaliacoes =  feignAvaliacaoClient.buscarTodosPorProduto(produtoId);
        } catch (Exception e) {
            logger.error("Erro ao buscar avaliações");
            throw (e);
        }

        logger.info("Alimentando o Cache");
        CACHE_AVALIACOES.put(produtoId, avaliacoes);
        return avaliacoes;
    }

    private List<AvaliacaoResponse> buscarTodosPorProdutoNoCacheFallback(Long produtoId, Throwable t){
        logger.info("Buscando no Cache");
        return CACHE_AVALIACOES.getOrDefault(produtoId, new ArrayList<>());
    }

}
