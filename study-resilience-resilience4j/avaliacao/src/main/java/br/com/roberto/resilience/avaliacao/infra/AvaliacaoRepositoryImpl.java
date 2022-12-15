package br.com.roberto.resilience.avaliacao.infra;

import br.com.roberto.resilience.avaliacao.domain.entity.Avaliacao;
import br.com.roberto.resilience.avaliacao.domain.repository.AvaliacaoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AvaliacaoRepositoryImpl implements AvaliacaoRepository {

    private static final List<Avaliacao> AVALIACOES = new ArrayList<>();
    private static long id = 1;

    //Inicializador dos statics
    static {
        AVALIACOES.add(new Avaliacao(nextId(), 10, "Carlos",
                "Esse Produto é muito bom .", 1L));
        AVALIACOES.add(new Avaliacao(nextId(), 1, "Paulo",
                "Esse Produto veio com defeito.", 1L));
        AVALIACOES.add(new Avaliacao(nextId(), 4, "Ramiro",
                "Esta travando muito.", 1L));

        AVALIACOES.add(new Avaliacao(nextId(), 8, "Tiago",
                "Esta funcionando bem.", 2L));
        AVALIACOES.add(new Avaliacao(nextId(), 5, "Alex",
                "Sem Linux.", 3L));
    }

    @Override
    public void save(Avaliacao avaliacao) {
        avaliacao.setId(nextId());
        AVALIACOES.add(avaliacao);
    }

    @Override
    public Optional<Avaliacao> getOne(Long id) {
        return AVALIACOES.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Avaliacao> getAll() {
        return new ArrayList<>(AVALIACOES);
    }

    private static long nextId() {
        return id++;
    }
}
