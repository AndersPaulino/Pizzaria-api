package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Produto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ProdutoDTO {

    private Long id;

    private boolean ativo;

    private LocalDateTime registro;

    private List<Pizza> pizzaList = new ArrayList<>();

    private List<Bebida> bebidaList = new ArrayList<>();

    private BigDecimal valorProduto;

    public ProdutoDTO(){

    }
    public ProdutoDTO(Produto produto){
        id = produto.getId();
        ativo = produto.isAtivo();
        registro = produto.getRegistro();
        pizzaList = produto.getPizzaList();
        bebidaList = produto.getBebidaList();
        valorProduto = produto.getValorProduto();
    }
}
