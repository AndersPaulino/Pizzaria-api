package com.pizzaria.app.controller;

import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
@CrossOrigin(origins = "*")
public class EnderecoController {
    private final EnderecoService enderecoService;

    @Autowired
    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<String> cadastrarEndereco(@RequestBody Endereco endereco) {
        try {
            enderecoService.cadastrar(endereco);
            return ResponseEntity.ok("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/bairro/{bairro}")
    public ResponseEntity<List<Endereco>> buscarEnderecosPorBairro(@PathVariable String bairro) {
        List<Endereco> enderecos = enderecoService.buscarEnderecosPorBairro(bairro);
        if (!enderecos.isEmpty()) {
            return ResponseEntity.ok(enderecos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/rua/{rua}")
    public ResponseEntity<List<Endereco>> buscarEnderecosPorRua(@PathVariable String rua) {
        List<Endereco> enderecos = enderecoService.buscarEnderecosPorRua(rua);
        if (!enderecos.isEmpty()) {
            return ResponseEntity.ok(enderecos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<List<Endereco>> buscarEnderecosPorNumero(@PathVariable int numero) {
        List<Endereco> enderecos = enderecoService.buscarEnderecosPorNumero(numero);
        if (!enderecos.isEmpty()) {
            return ResponseEntity.ok(enderecos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        List<EnderecoDTO> enderecosDTOS = enderecoService.findAll();
        return ResponseEntity.ok(enderecosDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        return enderecoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            enderecoService.atualizarEndereco(id, endereco);
            return ResponseEntity.ok().body("Registro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEndereco(@PathVariable Long id) {
        try {
            enderecoService.deletarEndereco(id);
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<EnderecoDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
