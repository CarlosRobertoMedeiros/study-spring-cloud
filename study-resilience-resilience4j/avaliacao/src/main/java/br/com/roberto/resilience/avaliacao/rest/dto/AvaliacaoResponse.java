package br.com.roberto.resilience.avaliacao.rest.dto;


import br.com.roberto.resilience.avaliacao.domain.entity.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvaliacaoResponse {

    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;

    public static AvaliacaoResponse of(Avaliacao avaliacao) {
        return new AvaliacaoResponse(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getDescricao(),
                avaliacao.getNomeAvaliador());
    }


}
