package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_venda", schema = "public")
public class Venda extends AbstractEntity {

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "venda_cliente",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id"))
    private Cliente cliente;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "venda_funcionario",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id"))
    private Funcionario funcionario;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "venda_produto",
            joinColumns = @JoinColumn(name = "venda_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private Produto produto;

    @Getter @Setter
    @Column(name = "emitir_nota")
    private boolean emitirNota;

    @Getter @Setter
    @Column(name = "entregar")
    private boolean entregar;

    @Getter @Setter
    @Column(name = "valor_venda")
    private BigDecimal valorVenda;

}
