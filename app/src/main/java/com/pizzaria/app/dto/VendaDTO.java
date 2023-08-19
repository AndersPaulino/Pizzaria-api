package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Venda;

import java.math.BigDecimal;

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

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
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

