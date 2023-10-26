package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco", schema = "public")
public class Endereco extends AbstractEntity {

    @Getter @Setter
    @Column(name = "cl_bairro", nullable = false, length = 255)
    private String bairro;

    @Getter @Setter
    @Column(name = "cl_rua", nullable = false, length = 255)
    private String rua;

    @Getter @Setter
    @Column(name = "cl_numero", nullable = false)
    private int numero;
}

