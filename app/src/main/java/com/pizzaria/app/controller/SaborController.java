package com.pizzaria.app.controller;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sabor")
public class SaborController {

    private SaborService saborService;

    @Autowired
    public SaborController(SaborService saborService){
        this.saborService = saborService;
    }

    @GetMapping("/{id}")
    public SaborDTO findById(@PathVariable Long id){
        return  saborService.findById(id);
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
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar bebida.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
