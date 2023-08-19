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
    private BebidaService bebidaService;

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

    @PostMapping
    public ResponseEntity<String> cadastrarBebida(@RequestBody Bebida bebida) {
        try {
            Bebida bebidaCadastrada = bebidaService.cadastrar(bebida);
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
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Bebida bebida) {
        try {
            bebida.setId(id);

            Bebida bebidaAtualizada = bebidaService.atualizar(bebida);

            if (bebidaAtualizada != null) {
                return ResponseEntity.ok("Registro atualizado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar bebida.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
