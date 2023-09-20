package com.pizzaria.app.controller;

import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sabor")
public class SaborController {

    private final SaborService saborService;

    @Autowired
    public SaborController(SaborService saborService) {
        this.saborService = saborService;
    }

    @GetMapping("/{id}")
    public SaborDTO findById(@PathVariable Long id) {
        return saborService.findById(id);
    }

    @GetMapping
    public List<SaborDTO> findAll() {
        return saborService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Sabor sabor) {
        try {
            Sabor saborCadastrado = saborService.cadastrar(sabor);
            if (saborCadastrado != null) {
                return ResponseEntity.ok("Registro cadastrado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar sabor.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody SaborDTO saborDTO) {
        try {
            if (!id.equals(saborDTO.getId())) {
                return ResponseEntity.badRequest().body("ID in the path variable does not match the ID in the request body.");
            }

            SaborDTO saborAtualizado = saborService.atualizar(saborDTO);

            if (saborAtualizado != null) {
                return ResponseEntity.ok("Registro atualizado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar sabor.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            saborService.deletar(id);
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
