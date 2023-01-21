package br.com.roberto.clientes.transportlayer.v1.impl.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ClienteDto {
    private String nome;
    private String idade;
}
