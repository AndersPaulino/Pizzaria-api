package com.pizzaria.app.service;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO findById(Long id){
        Produto entity = produtoRepository.findById(id).get();
        ProdutoDTO dto = new ProdutoDTO(entity);
        return dto;
    }
}