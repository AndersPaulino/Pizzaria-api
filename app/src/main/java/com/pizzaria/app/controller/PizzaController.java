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
import java.util.Optional;

@RestController
@RequestMapping("/pizza")
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
    public ResponseEntity<String> cadastrar(@RequestBody PizzaDTO pizzaDTO) {

        try {

            List<SaborDTO> saborDTOs = new ArrayList<>();
            for (Sabor saborDTO : pizzaDTO.getSabor()) {
                Optional<SaborDTO> saborOptional = Optional.ofNullable(saborService.findById(saborDTO.getId()));
                if (saborOptional.isPresent()) {
                    saborDTOs.add(saborOptional.get());
                } else {
                    return null;
                }
            }


            Pizza pizza = new Pizza();
            pizza.setSabor(pizzaDTO.getSabor());
            pizza.setTamanho(pizzaDTO.getTamanho());
            pizza.setValorPizza(pizzaDTO.getValorPizza());

            pizzaService.cadastrarPizza(pizza);
            return ResponseEntity.ok("Registro cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
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
