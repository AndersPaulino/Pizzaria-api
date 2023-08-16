package com.pizzaria.app.controller;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.service.SaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sabor")
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
}
