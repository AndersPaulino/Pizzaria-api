package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_produto", schema = "public")
public class Produto extends AbstractEntity{

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "cl_produto.pizza",
            joinColumns =  @JoinColumn(
                    name = "produto.id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "pizza.id"
            )
    )
    private List<Pizza> pizzaList = new ArrayList<>();

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "cl_produto.bebida",
            joinColumns =  @JoinColumn(
                    name = "produto.id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "bebida.id"
            )
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
