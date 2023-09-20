package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bebida")
public class BebidaController {
    private final BebidaService bebidaService;

    @Autowired
    public BebidaController (BebidaService bebidaService){
        this.bebidaService = bebidaService;
    }

    @GetMapping("/{id}")
    public BebidaDTO findById(@PathVariable Long id){
        return  bebidaService.findById(id);
    }

    @GetMapping
    public List<BebidaDTO> findAll() {
        return bebidaService.findAll();
    }
    @GetMapping("/nome/{nomeBebida}")
    public BebidaDTO findByName(@PathVariable String nomeBebida){
        return bebidaService.findByName(nomeBebida);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarBebida(@RequestBody BebidaDTO bebidaDTO) {
        try {
            BebidaDTO bebidaCadastrada = bebidaService.cadastrar(bebidaDTO);

            if (bebidaCadastrada != null) {
                return ResponseEntity.ok("Registro cadastrado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar bebida.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarBebida(@PathVariable Long id, @RequestBody BebidaDTO bebidaDTO) {
        try {
            bebidaDTO.setId(id);
            BebidaDTO bebidaAtualizada = bebidaService.atualizarBebida(bebidaDTO);

            if (bebidaAtualizada != null) {
                return ResponseEntity.ok("Registro atualizado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar bebida.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarBebida(@PathVariable Long id) {
        try {
            bebidaService.deleteBebida(id);
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
