package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bebida")
public class BebidaController {
    private final BebidaService bebidaService;

    @Autowired
    public BebidaController(BebidaService bebidaService) {
        this.bebidaService = bebidaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebidaDTO> findById(@PathVariable Long id) {
        BebidaDTO bebidaDTO = bebidaService.findById(id);
        return bebidaDTO != null
                ? ResponseEntity.ok(bebidaDTO)
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<BebidaDTO> findAll() {
        return bebidaService.findAll();
    }

    @GetMapping("/nome/{nomeBebida}")
    public List<BebidaDTO> findByName(@PathVariable String nomeBebida) {
        return bebidaService.findByName(nomeBebida);
    }

    @PostMapping
    public ResponseEntity<String> cadastrarBebida(@RequestBody BebidaDTO bebidaDTO) {
        try {
            BebidaDTO bebidaCadastrada = bebidaService.cadastrar(bebidaDTO);
            return ResponseEntity.ok("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarBebida(@PathVariable Long id, @RequestBody BebidaDTO bebidaDTO) {
        try {
            bebidaDTO.setId(id);
            BebidaDTO bebidaAtualizada = bebidaService.atualizarBebida(id, bebidaDTO);

            if (bebidaAtualizada != null) {
                return ResponseEntity.ok("Registro atualizado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar bebida.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarBebida(@PathVariable Long id) {
        try {
            bebidaService.deleteBebida(id);
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
