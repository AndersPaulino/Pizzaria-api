package com.pizzaria.app.controller;

import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.entity.Venda;
import com.pizzaria.app.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")

@CrossOrigin(origins = "*")
public class VendaController {


    private VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService){
        this.vendaService = vendaService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Venda> findaById(@PathVariable Long id){
        return vendaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll(){
        List<VendaDTO> vendaDTOS = vendaService.findAll();
        return ResponseEntity.ok(vendaDTOS);
    }
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Venda venda){
        try {
            vendaService.cadastrar(venda);
            return ResponseEntity.ok().body("Registro cadastrado com sucesso!");
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/id")
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody Venda venda){
        try {
            vendaService.atualizar(id, venda);
            return ResponseEntity.ok("Registro atualizado com sucesso!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarVenda(@PathVariable Long id){
        try {
            vendaService.deleteVenda(id);
            return ResponseEntity.ok("Registro deletado com sucesso!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(e.getMessage());
        }
    }
    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<String> desativar(@PathVariable Long id){
        try {
            vendaService.desativar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro.");

        }
    }
}
