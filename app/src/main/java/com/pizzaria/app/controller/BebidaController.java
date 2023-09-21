package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public List<BebidaDTO> findByName(@PathVariable String nomeBebida) {
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
            Long idBebidaCadastrada = bebidaCadastrada.getId();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/api/bebida/" + idBebidaCadastrada);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .headers(headers)
                    .body("Registro cadastrado com sucesso! ID da bebida: " + idBebidaCadastrada);
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
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarBebida(@PathVariable Long id) {
        try {
            bebidaService.deleteBebida(id);
            return ResponseEntity.ok("Registro excluído com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
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
