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

    @Getter @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Endereco(String bairro, String rua, int numero) {}
}

