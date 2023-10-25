package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.entity.Venda;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class VendaDTO {

    private Long id;
    private Cliente cliente;
    private Funcionario funcionario;
    private List<Produto> produto = new ArrayList<>();
    private boolean emitirNota;
    private boolean entregar;
    private BigDecimal valorVenda;

    public VendaDTO(Long id, Cliente cliente, Funcionario funcionario, List<Produto> produto, boolean emitirNota, boolean entregar, BigDecimal valorVenda) {
        this.id = id;
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.produto = produto;
        this.emitirNota = emitirNota;
        this.entregar = entregar;
        this.valorVenda = valorVenda;
    }

    public VendaDTO(Venda venda) {
        id = venda.getId();
        cliente = venda.getCliente();
        funcionario = venda.getFuncionario();
        produto = venda.getProduto();
        emitirNota = venda.isEmitirNota();
        entregar = venda.isEntregar();
        valorVenda = venda.getValorVenda();
    }
}

