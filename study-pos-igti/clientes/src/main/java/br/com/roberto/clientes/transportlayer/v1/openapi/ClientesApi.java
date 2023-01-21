package br.com.roberto.clientes.transportlayer.v1.openapi;

import br.com.roberto.clientes.transportlayer.v1.impl.input.ClienteInput;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Clientes")
public interface ClientesApi {

    ResponseEntity<List<ClienteInput>> getClientes();

}
