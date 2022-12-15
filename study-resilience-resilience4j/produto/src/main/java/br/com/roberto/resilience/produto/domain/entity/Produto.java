package br.com.roberto.resilience.produto.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {
    private Long id;
    private String nome;
}
