package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_cliente", schema = "public")
public class Cliente extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, length = 18)
    private String cpf;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "cliente_endereco",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    private List<Endereco> endereco = new ArrayList<>();
}


