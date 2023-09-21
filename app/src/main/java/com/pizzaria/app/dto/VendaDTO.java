package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Venda;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class VendaDTO {

    private Long id;
    private ClienteDTO cliente;
    private FuncionarioDTO funcionario;
    private ProdutoDTO produto;
    private boolean emitirNota;
    private boolean entregar;
    private BigDecimal valorVenda;

    public VendaDTO() {}

    public VendaDTO(Venda venda) {
        this.id = venda.getId();
        this.cliente = new ClienteDTO(venda.getCliente());
        this.funcionario = new FuncionarioDTO(venda.getFuncionario());
        this.produto = new ProdutoDTO(venda.getProduto());
        this.emitirNota = venda.isEmitirNota();
        this.entregar = venda.isEntregar();
        this.valorVenda = venda.getValorVenda();
    }
}

