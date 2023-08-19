package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pizza", schema = "public")
public class Pizza extends AbstractEntity{

    @Getter @Setter
    @OneToMany
    @JoinTable(name = "cl_pizza.sabor",
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {
                            "pizza.id",
                            "sabor.id"
                    }
            ),
            joinColumns =  @JoinColumn(
                    name = "pizza.id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "sabor.id"
            )
    )
    private List<Sabor> sabor = new ArrayList<>();

    @Getter @Setter
    private Tamanho tamanho;

    @Getter @Setter
    @Column(name = "cl_qtde_sabor")
    private int qtdeSabor;

    @Getter @Setter
    @Column(name = "cl_valor_pizza")
    private BigDecimal valorPizza;

    public Pizza() {
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.qtdeSabor = qtdeSabor;
        this.valorPizza = valorPizza;
    }

}
