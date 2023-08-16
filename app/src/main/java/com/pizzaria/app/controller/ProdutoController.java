package com.pizzaria.app.controller;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    public ProdutoDTO findById(@PathVariable Long id){
        return  produtoService.findById(id);
    }
}
