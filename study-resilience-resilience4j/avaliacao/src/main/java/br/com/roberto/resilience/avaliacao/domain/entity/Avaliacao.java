package br.com.roberto.resilience.avaliacao.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Avaliacao {
    private Long id;
    private Integer nota;
    private String descricao;
    private String nomeAvaliador;
    private Long produtoId;
}
