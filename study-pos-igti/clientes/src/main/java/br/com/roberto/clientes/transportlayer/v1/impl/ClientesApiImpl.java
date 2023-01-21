package br.com.roberto.clientes.transportlayer.impl;


import br.com.roberto.clientes.transportlayer.input.ClienteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/clientes")
@Slf4j
public class ClientesApiImpl {

    private List<ClienteDto> clientes;

    public ClientesApiImpl(List<ClienteDto> clientes) {
        clientes = Arrays.asList(new ClienteDto("Carlos","41"),
                new ClienteDto("Luciene","39"),
                new ClienteDto("Yan Victor","39"),
                new ClienteDto("Matheus","8"),
                new ClienteDto("Vinicius","4")
        );
        this.clientes = clientes;

    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getClientes(){


        try{

        }catch (Exception e){
            log.error("Problema na comunicação ao Consulta clientes: "+e.getMessage()+" "+e.getCause());
        }

        return ResponseEntity.ok(clientes);
    }


}
