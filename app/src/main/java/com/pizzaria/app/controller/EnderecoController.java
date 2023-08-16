package com.pizzaria.app.controller;

import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarTodosEnderecos() {
        List<Endereco> enderecos = enderecoService.listarTodosEnderecos();
        if (!enderecos.isEmpty()) {
            return ResponseEntity.ok(enderecos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/bairro/{bairro}")
    public ResponseEntity<List<Endereco>> getEnderecosByBairro(@PathVariable String bairro) {
        List<Endereco> enderecos = enderecoService.buscarPorBairro(bairro);
        if (!enderecos.isEmpty()) {
            return ResponseEntity.ok(enderecos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/rua/{rua}")
    public ResponseEntity<List<Endereco>> getEnderecosByRua(@PathVariable String rua) {
        List<Endereco> enderecos = enderecoService.buscarPorRua(rua);
        if (!enderecos.isEmpty()) {
            return ResponseEntity.ok(enderecos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<Endereco>> getEnderecosByNumero(@PathVariable int numero) {
        List<Endereco> enderecos = enderecoService.buscarPorNumero(numero);
        if (!enderecos.isEmpty()) {
            return ResponseEntity.ok(enderecos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.cadastrarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, endereco);
        if (enderecoAtualizado != null) {
            return ResponseEntity.ok(enderecoAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.noContent().build();
    }
}
