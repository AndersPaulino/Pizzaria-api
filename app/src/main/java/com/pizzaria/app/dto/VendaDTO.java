package com.pizzaria.app.dto;

import java.math.BigDecimal;

public class VendaDTO {

    private Long id;
    private ClienteDTO cliente;
    private FuncionarioDTO funcionario;
    private PedidoDTO pedido;
    private boolean emitirNota;
    private boolean entregar;
    private BigDecimal valorVenda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public FuncionarioDTO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDTO funcionario) {
        this.funcionario = funcionario;
    }

    public PedidoDTO getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDTO pedido) {
        this.pedido = pedido;
    }

    public boolean isEmitirNota() {
        return emitirNota;
    }

    public void setEmitirNota(boolean emitirNota) {
        this.emitirNota = emitirNota;
    }

    public boolean isEntregar() {
        return entregar;
    }

    public void setEntregar(boolean entregar) {
        this.entregar = entregar;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }
}
