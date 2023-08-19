package com.pizzaria.app.controller;

import com.pizzaria.app.dto.FuncionarioDTO;
import com.pizzaria.app.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodosFuncionarios() {
        List<FuncionarioDTO> funcionariosDTO = funcionarioService.listarTodosFuncionariosDTO();
        return ResponseEntity.ok(funcionariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarFuncionarioPorId(@PathVariable Long id) {
        Optional<FuncionarioDTO> funcionarioDTO = funcionarioService.buscarFuncionarioPorIdDTO(id);
        return funcionarioDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<FuncionarioDTO>> buscarFuncionariosPorNome(@RequestParam String nome) {
        List<FuncionarioDTO> funcionariosDTO = funcionarioService.buscarFuncionariosPorNomeDTO(nome);
        return ResponseEntity.ok(funcionariosDTO);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioDTO funcionarioCriado = funcionarioService.cadastrarFuncionario(funcionarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionarioDTO);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
