package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("ativo/{ativo}")
    public ResponseEntity<List<BebidaDTO>> findByAtivo(@PathVariable boolean ativo) {
        try {
            List<BebidaDTO> bebidaDTOS = bebidaService.findByAtivo(ativo);

            if (!bebidaDTOS.isEmpty()) {
                return ResponseEntity.ok(bebidaDTOS);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/nome/{nomeBebida}")
    public BebidaDTO findByName(@PathVariable String nomeBebida){
        return bebidaService.findByName(nomeBebida);
    }

    @GetMapping("registro/dia/{registro}")
    public ResponseEntity<List<BebidaDTO>> findByDiaRegistro(@PathVariable("registro") LocalDate registro) {
        try {
            List<BebidaDTO> bebidaDTOS = bebidaService.findByDiaRegistro(registro);

            if (!bebidaDTOS.isEmpty()) {
                return ResponseEntity.ok(bebidaDTOS);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("atualizar/dia/{atualizar}")
    public ResponseEntity<List<BebidaDTO>> findByDiaAtualizar(@PathVariable("atualizar") LocalDate atualizar) {
        try {
            List<BebidaDTO> bebidaDTOS = bebidaService.findByDiaAtualizar(atualizar);

            if (!bebidaDTOS.isEmpty()) {
                return ResponseEntity.ok(bebidaDTOS);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarBebida(@PathVariable Long id) {
        try {
            bebidaService.deleteBebida(id);
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            bebidaService.desativar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro.");
        }
    }
}
