package br.com.roberto.clientes.transportlayer.v1.impl.input;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ClienteInput {
    private String nome;
    private String idade;
}
