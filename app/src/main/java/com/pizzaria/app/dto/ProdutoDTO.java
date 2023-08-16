package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getRegistro() {
        return registro;
    }

    public void setRegistro(LocalDateTime registro) {
        this.registro = registro;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public List<Bebida> getBebidaList() {
        return bebidaList;
    }

    public void setBebidaList(List<Bebida> bebidaList) {
        this.bebidaList = bebidaList;
    }

    public BigDecimal getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(BigDecimal valorProduto) {
        this.valorProduto = valorProduto;
    }
}
