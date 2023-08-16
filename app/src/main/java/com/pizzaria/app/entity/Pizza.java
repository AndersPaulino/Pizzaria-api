package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pizza", schema = "public")
public class Pizza extends AbstractEntity{

    @Getter @Setter
    @OneToMany
    @JoinColumn(name = "cl_sabor")
    private List<Sabor> sabor = new ArrayList<>();

    @Getter @Setter
    private Tamanho tamanho;

    @Getter @Setter
    @Column(name = "cl_qtde_sabor")
    private int qtdeSabor;

}
