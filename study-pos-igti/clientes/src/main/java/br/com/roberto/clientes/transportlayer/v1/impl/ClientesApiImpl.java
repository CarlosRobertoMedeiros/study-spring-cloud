package br.com.roberto.clientes.transportlayer.v1.impl;


import br.com.roberto.clientes.transportlayer.v1.impl.input.ClienteInput;
import br.com.roberto.clientes.transportlayer.v1.openapi.ClientesApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
@Slf4j
public class ClientesApiImpl implements ClientesApi {

    private List<ClienteInput> clientes;

    public ClientesApiImpl(List<ClienteInput> clientes) {
        clientes = Arrays.asList(new ClienteInput("Carlos","41"),
                new ClienteInput("Luciene","39"),
                new ClienteInput("Yan Victor","39"),
                new ClienteInput("Matheus","8"),
                new ClienteInput("Vinicius","4")
        );
        this.clientes = clientes;

    }

    @Override
    @GetMapping
    public ResponseEntity<List<ClienteInput>> getClientes() {
        try{

        }catch (Exception e){
            log.error("Problema na comunicação ao Consulta clientes: "+e.getMessage()+" "+e.getCause());
        }

        return ResponseEntity.ok(clientes);
    }

}
