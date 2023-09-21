package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.entity.Tamanho;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class PizzaDTO {
    private Long id;

    private boolean ativo;

    private LocalDateTime registro;

    private List<Sabor> sabor = new ArrayList<>();

    private Tamanho tamanho;

    private BigDecimal valorPizza;

    private int qtdeSabor;

    public PizzaDTO(){}
    public Pizza toPizza() {
        Pizza pizza = new Pizza();
        pizza.setId(id);
        pizza.setAtivo(ativo);
        pizza.setRegistro(registro);
        pizza.setSabor(sabor);
        pizza.setTamanho(tamanho);
        pizza.setValorPizza(valorPizza);
        pizza.setQtdeSabor(qtdeSabor);
        return pizza;
    }

    public PizzaDTO(Pizza pizza){
        id = pizza.getId();
        ativo = pizza.isAtivo();
        registro = pizza.getRegistro();
        sabor = pizza.getSabor();
        tamanho = pizza.getTamanho();
        valorPizza = pizza.getValorPizza();
        qtdeSabor = pizza.getQtdeSabor();
    }
    public static PizzaDTO fromPizza(Pizza pizza) {
        return new PizzaDTO(pizza);
    }
}
