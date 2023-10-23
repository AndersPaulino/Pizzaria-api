package com.pizzaria.app.controller;

import com.pizzaria.app.dto.FuncionarioDTO;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        List<FuncionarioDTO> funcionariosDTO = funcionarioService.findAll();
        return ResponseEntity.ok(funcionariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        return funcionarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FuncionarioDTO>> buscarFuncionariosPorNome(@RequestParam String nome) {
        List<FuncionarioDTO> funcionariosDTO = funcionarioService.buscarFuncionariosPorNomeDTO(nome);
        return ResponseEntity.ok(funcionariosDTO);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            funcionarioService.cadastrar(funcionario);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        try {
            funcionarioService.atualizarFuncionario(id, funcionario);
            return ResponseEntity.ok().body("Registro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
