package br.com.roberto.observabilidade.rest;

import java.time.LocalDateTime;

public class ClienteTO {

    private String nome;
    private String sobrenome;
    private LocalDateTime data;

    public ClienteTO(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data = LocalDateTime.now();
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public LocalDateTime getData() {
        return data;
    }

}
