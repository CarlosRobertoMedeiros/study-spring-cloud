package br.com.roberto.sistemaa.cilents.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    public String nome;
    public Integer idade;
    public String endereco;
}
