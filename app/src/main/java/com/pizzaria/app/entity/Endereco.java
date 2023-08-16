package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco", schema = "public")
public class Endereco extends AbstractEntity {

    @Getter @Setter
    @Column(name = "cl_bairro")
    private String bairro;

    @Getter @Setter
    @Column(name = "cl_rua")
    private String rua;

    @Getter @Setter
    @Column(name = "cl_numero")
    private int numero;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}

