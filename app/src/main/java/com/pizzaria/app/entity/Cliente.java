package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "tb_cliente", schema = "public")
public class Cliente extends AbstractEntity {

    @Getter @Setter
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Getter @Setter
    @Column(name = "cpf", nullable = false, length = 18)
    private String cpf;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    @Getter @Setter
    private Endereco endereco;
}
