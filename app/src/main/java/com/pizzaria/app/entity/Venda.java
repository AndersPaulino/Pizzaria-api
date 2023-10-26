package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_venda", schema = "public")
public class Venda extends AbstractEntity {

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "cl_venda.cliente",
            joinColumns =  @JoinColumn(name = "venda.id"),
            inverseJoinColumns = @JoinColumn(name = "cliente.id"))
    private List<Cliente> cliente = new ArrayList<>();

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "cl_venda.funcionario",
            joinColumns =  @JoinColumn(name = "venda.id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario.id"))
    private List<Funcionario> funcionario = new ArrayList<>();

    @Getter @Setter
    @ManyToMany
    @JoinTable(name = "cl_venda_produto",
            joinColumns = @JoinColumn(name = "venda.id"),
            inverseJoinColumns = @JoinColumn(name = "produto.id"))
    private List<Produto> produto = new ArrayList<>();

    @Getter @Setter
    @Column(name = "cl_emitir_nota")
    private boolean emitirNota;

    @Getter @Setter
    @Column(name = "cl_entregar")
    private boolean entregar;

    @Getter @Setter
    @Column(name = "cl_valor_venda")
    private BigDecimal valorVenda;

}
