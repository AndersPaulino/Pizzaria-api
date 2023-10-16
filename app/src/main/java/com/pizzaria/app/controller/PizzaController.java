package com.pizzaria.app.controller;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.service.PizzaService;
import com.pizzaria.app.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pizza")
public class PizzaController {

    private final PizzaService pizzaService;
    private final SaborService saborService;

    @Autowired
    public PizzaController(PizzaService pizzaService, SaborService saborService) {
        this.pizzaService = pizzaService;
        this.saborService = saborService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaDTO> findById(@PathVariable Long id) {
        PizzaDTO pizzaDTO = pizzaService.findById(id);
        return ResponseEntity.ok(pizzaDTO);
    }

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> findAll() {
        List<PizzaDTO> pizzaDTOs = pizzaService.findAll();
        return ResponseEntity.ok(pizzaDTOs);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Pizza pizza) {
        try {
            List<Sabor> sabores = pizza.getSabor();

            if (sabores == null || sabores.isEmpty()) {
                return ResponseEntity.badRequest().body("A pizza deve ter pelo menos um sabor.");
            }

            for (Sabor sabor : sabores) {
                Optional<SaborDTO> saborOptional = saborService.findById(sabor.getId());

                if (saborOptional.isEmpty()) {
                    return ResponseEntity.badRequest().body("Sabor não encontrado com o ID: " + sabor.getId());
                }
            }

            pizzaService.cadastrarPizza(pizza);
            return ResponseEntity.ok("Registro cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }




    @PutMapping("/{id}")
    public ResponseEntity<PizzaDTO> atualizarPizza(@PathVariable Long id, @RequestBody PizzaDTO pizzaDTO) {
        PizzaDTO pizzaAtualizada = new PizzaDTO(pizzaService.atualizarPizza(id, pizzaDTO.toPizza()));

        return ResponseEntity.ok(pizzaAtualizada);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPizza(@PathVariable Long id) {
        pizzaService.deletarPizza(id);
        return ResponseEntity.ok("Pizza com ID " + id + " excluída com sucesso.");
    }

}
