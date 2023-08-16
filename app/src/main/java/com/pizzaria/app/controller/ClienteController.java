package com.pizzaria.app.controller;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getClienteById(@PathVariable Long id) {
        ClienteDTO clienteDTO = clienteService.findById(id);
        if (clienteDTO != null) {
            return ResponseEntity.ok(clienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes() {
        List<ClienteDTO> clientesDTO = clienteService.listarTodosClientes();
        if (!clientesDTO.isEmpty()) {
            return ResponseEntity.ok(clientesDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<ClienteDTO>> getClientesByNome(@PathVariable String nome) {
        List<ClienteDTO> clientesDTO = clienteService.buscarPorNome(nome);
        if (!clientesDTO.isEmpty()) {
            return ResponseEntity.ok(clientesDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteDTO> getClienteByCpf(@PathVariable String cpf) {
        ClienteDTO clienteDTO = clienteService.buscarPorCpf(cpf);
        if (clienteDTO != null) {
            return ResponseEntity.ok(clienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/telefone/{telefone}")
    public ResponseEntity<List<ClienteDTO>> getClientesByTelefone(@PathVariable String telefone) {
        List<ClienteDTO> clientesDTO = clienteService.buscarPorTelefone(telefone);
        if (!clientesDTO.isEmpty()) {
            return ResponseEntity.ok(clientesDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> createCliente(@RequestBody ClienteDTO clienteDTO) {
        Cliente novoCliente = clienteService.cadastrarCliente(clienteDTO);
        ClienteDTO novoClienteDTO = new ClienteDTO(novoCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoClienteDTO);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteDTO);
        if (clienteAtualizado != null) {
            ClienteDTO clienteAtualizadoDTO = new ClienteDTO(clienteAtualizado);
            return ResponseEntity.ok(clienteAtualizadoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
