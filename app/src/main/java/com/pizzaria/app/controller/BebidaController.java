package com.pizzaria.app.controller;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bebida")
public class BebidaController {
    private BebidaService bebidaService;

    @Autowired
    public BebidaController (BebidaService bebidaService){
        this.bebidaService = bebidaService;
    }

    @GetMapping("/{id}")
    public BebidaDTO findById(@PathVariable Long id){
        return  bebidaService.findById(id);
    }

}
