package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

@Entity
@Table(name = "tb_produto", schema = "public")
public class Produto extends AbstractEntity{

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "tb_pedido_pizza",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> pizzaList = new ArrayList<>();

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "tb_pedido_bebida",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "bebida_id")
    )
    private List<Bebida> bebidaList = new ArrayList<>();
    @Getter @Setter
    @Column(name = "cl_valor_pedido")
    private BigDecimal valorProduto;

    public void calcularValorProduto() {
        BigDecimal valorTotal = BigDecimal.ZERO;

        if (pizzaList != null) {
            for (Pizza pizza : pizzaList) {
                valorTotal = valorTotal.add(pizza.getValorPizza());
            }
        }

        if (bebidaList != null) {
            for (Bebida bebida : bebidaList) {
                valorTotal = valorTotal.add(bebida.getValorBebida());
            }
        }

        valorProduto = valorTotal;
    }

}
