package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
