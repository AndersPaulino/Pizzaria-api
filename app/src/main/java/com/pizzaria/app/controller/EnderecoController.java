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

    private EnderecoService enderecoService;
    @Autowired
    public EnderecoController(EnderecoService enderecoService){
        this.enderecoService = enderecoService;
    }
    @GetMapping("/bairro/{bairro}")
    public List<Endereco> buscarEnderecosPorBairro(@PathVariable String bairro) {
        return enderecoService.buscarEnderecosPorBairro(bairro);
    }

    @GetMapping("/rua/{rua}")
    public List<Endereco> buscarEnderecosPorRua(@PathVariable String rua) {
        return enderecoService.buscarEnderecosPorRua(rua);
    }

    @GetMapping("/numero/{numero}")
    public List<Endereco> buscarEnderecosPorNumero(@PathVariable int numero) {
        return enderecoService.buscarEnderecosPorNumero(numero);
    }

    @GetMapping
    public List<Endereco> listarEnderecos() {
        return enderecoService.listarTodosEnderecos();
    }

    @GetMapping("/{id}")
    public Endereco buscarEnderecoPorId(@PathVariable Long id) {
        return enderecoService.buscarEnderecoPorId(id).orElse(null);
    }
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Endereco endereco){
        try {
            enderecoService.cadastrar(endereco);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            enderecoService.atualizar(id, endereco);
            return ResponseEntity.ok().body("Registro atualizado com sucesso!");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarEndereco(@PathVariable Long id) {
        try {
            enderecoService.deleteEndereco(id);
            return ResponseEntity.ok("Registro excluido com sucesso!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
