package com.pizzaria.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_funcionario", schema = "public")
public class Funcionario extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
}

