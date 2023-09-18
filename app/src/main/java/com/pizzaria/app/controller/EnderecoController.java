package com.pizzaria.app.controller;

import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public EnderecoDTO criarEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        return enderecoService.criarEndereco(enderecoDTO);
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

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id, @RequestBody EnderecoDTO enderecoDTO) {
        try {
            Endereco enderecoAtualizado = enderecoService.atualizarEndereco(id, enderecoDTO);
            if (enderecoAtualizado != null) {
                return ResponseEntity.ok(enderecoAtualizado);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deletarEndereco(@PathVariable Long id) {
        enderecoService.deletarEndereco(id);
    }
}
