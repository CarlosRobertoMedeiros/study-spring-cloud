package br.com.roberto.sistemaa.cilents.transportlayers.impl;

import br.com.roberto.sistemaa.cilents.entity.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRest {

    private Client client;
    private List<Client> clients = new ArrayList<>();


    @GetMapping
    public ResponseEntity<?> getAll(){
        clients.add(new Client("Carlos",40,"Esqueci 1"));
        clients.add(new Client("Luciene",38,"Esqueci 2"));
        clients.add(new Client("Matheus",8,"Esqueci 3"));
        clients.add(new Client("Vinicius",5,"Esqueci 4"));
        return ResponseEntity.ok().body(clients);

    }


}
