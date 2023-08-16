package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.entity.Tamanho;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PizzaDTO {
    private Long id;

    private boolean ativo;

    private LocalDateTime registro;

    private List<Sabor> sabor = new ArrayList<>();

    private Tamanho tamanho;

    private BigDecimal valorPizza;

    public PizzaDTO(){

    }

    public PizzaDTO(Pizza pizza){
        id = pizza.getId();
        ativo = pizza.isAtivo();
        registro = pizza.getRegistro();
        sabor = pizza.getSabor();
        tamanho = pizza.getTamanho();
        valorPizza = pizza.getValorPizza();
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

    public List<Sabor> getSabor() {
        return sabor;
    }

    public void setSabor(List<Sabor> sabor) {
        this.sabor = sabor;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public BigDecimal getValorPizza() {
        return valorPizza;
    }

    public void setValorPizza(BigDecimal valorPizza) {
        this.valorPizza = valorPizza;
    }
}