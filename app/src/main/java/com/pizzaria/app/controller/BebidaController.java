package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.repository.BebidaRepository;
import com.pizzaria.app.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bebida")
public class BebidaController {
    private final BebidaService bebidaService;
    private final BebidaRepository bebidaRepository;

    @Autowired
    public BebidaController(BebidaService bebidaService,
                            BebidaRepository bebidaRepository) {
        this.bebidaService = bebidaService;
        this.bebidaRepository = bebidaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BebidaDTO> findById(@PathVariable Long id) {
       return bebidaService.findById(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<BebidaDTO>> findAll() {
        List<BebidaDTO> bebidaDTOS = bebidaService.findAll();
        return ResponseEntity.ok(bebidaDTOS);
    }

    @GetMapping("/nome/{nomeBebida}")
    public ResponseEntity<BebidaDTO> findByName(@PathVariable String nomeBebida) {
        try{
            BebidaDTO bebidaDTO = bebidaService.findByName(nomeBebida);

            if (bebidaDTO != null){
                return ResponseEntity.ok(bebidaDTO);
            } else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
    public ResponseEntity<String> cadastrarBebida(@RequestBody Bebida bebida) {
        try {
            bebidaService.cadastrar(bebida);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarBebida(@PathVariable Long id, @RequestBody Bebida bebida) {
        try {
            bebidaService.atualizar(id, bebida);
            return ResponseEntity.ok().body("Registro atualizado com sucesso!");
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
