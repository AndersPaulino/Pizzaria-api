package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    private PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }
    @GetMapping("/{id}")
    public PizzaDTO findById(@PathVariable Long id){
        return  pizzaService.findById(id);
    }

    @GetMapping
    public List<PizzaDTO> findAll() {
        return pizzaService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Pizza pizza) {
        try {
            Pizza pizzaCadastrada = pizzaService.cadastrar(pizza);
            if (pizzaCadastrada != null) {
                return ResponseEntity.ok("Registro cadastrado com sucesso!");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar bebida.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
