package br.com.roberto.resilience.produto.infra.database;

import br.com.roberto.resilience.produto.domain.entity.Produto;
import br.com.roberto.resilience.produto.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {
    private static final List<Produto> PRODUTOS = new ArrayList<>();
    private static long id = 1;

    //Inicializador dos statics
    static {
        PRODUTOS.add(new Produto(nextId(), "Desktop Ryzen 5 5800x 32GB"));
        PRODUTOS.add(new Produto(nextId(), "LeNovo Legion Y 1020"));
        PRODUTOS.add(new Produto(nextId(), "Samsung 1533"));
    }

    @Override
    public void save(Produto produto) {
        produto.setId(nextId());
        PRODUTOS.add(produto);
    }

    @Override
    public Optional<Produto> getOne(Long id) {
        return PRODUTOS.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public List<Produto> getAll() {
        return new ArrayList<>(PRODUTOS);
    }

    private static long nextId() {
        return id++;
    }
}
