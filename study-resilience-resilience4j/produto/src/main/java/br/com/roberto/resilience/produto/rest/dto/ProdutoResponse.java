package br.com.roberto.resilience.produto.rest.dto;

import br.com.roberto.resilience.produto.client.AvaliacaoResponse;
import br.com.roberto.resilience.produto.domain.entity.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoResponse {
    private Long id;
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<AvaliacaoResponse> avaliacoes;

    public static ProdutoResponse of(Produto produto, List<AvaliacaoResponse> avaliacoes) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                avaliacoes
        );
    }

}
