package br.com.roberto.resilience.produto.client;

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
}
