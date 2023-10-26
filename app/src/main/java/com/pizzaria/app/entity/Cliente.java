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
    @Column(name = "cl_nome", nullable = false, length = 255)
    private String nome;

    @Getter @Setter
    @Column(name = "cl_cpf", nullable = false, length = 18)
    private String cpf;

    @Getter @Setter
    @OneToMany
    @JoinTable(name = "cl_cliente.endereco",
            joinColumns =  @JoinColumn(
                    name = "cliente.id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "endereco.id"
            )
    )
    private List<Endereco> endereco = new ArrayList<>();
}