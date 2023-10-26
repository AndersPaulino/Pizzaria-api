package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public BigDecimal calcularValorProduto() {
        BigDecimal valorPizzas = calcularValorPizzas();
        BigDecimal valorBebidas = calcularValorBebidas();
        return valorPizzas.add(valorBebidas);
    }
    private BigDecimal calcularValorPizzas() {
        if (Objects.nonNull(pizzaList)) {
            return pizzaList.stream()
                    .map(Pizza::getValorPizza)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }
    private BigDecimal calcularValorBebidas() {
        if (Objects.nonNull(bebidaList)) {
            return bebidaList.stream()
                    .map(Bebida::getValorBebida)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        return BigDecimal.ZERO;
    }
}
