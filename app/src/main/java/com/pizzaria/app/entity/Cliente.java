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
    @Column(name = "cl_nome")
    private String nome;

    @Getter @Setter
    @Column(name = "cl_telefone")
    private String telefone;

    @Getter @Setter
    @Column(name = "cl_cpf")
    private String cpf;

    @Getter @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

}
