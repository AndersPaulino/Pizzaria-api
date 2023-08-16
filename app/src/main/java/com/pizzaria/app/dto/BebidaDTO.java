package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Bebida;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BebidaDTO {
    private Long id;
    private String nomeBebida;
    private BigDecimal valorBebida;

    private boolean ativo;

    private LocalDateTime registro;

    public BebidaDTO(BebidaDTO bebida) {

    }

    public BebidaDTO(Long id, String nomeBebida, BigDecimal valorBebida, boolean ativo, LocalDateTime registro) {
        this.id = id;
        this.nomeBebida = nomeBebida;
        this.valorBebida = valorBebida;
        this.ativo = ativo;
        this.registro = registro;
    }

    public BebidaDTO(Bebida bebida) {
        id = bebida.getId();
        nomeBebida = bebida.getNomeBebida();
        valorBebida = bebida.getValorBebida();
        ativo = bebida.isAtivo();
        registro = bebida.getRegistro();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBebida() {
        return nomeBebida;
    }

    public void setNomeBebida(String nomeBebida) {
        this.nomeBebida = nomeBebida;
    }

    public BigDecimal getValorBebida() {
        return valorBebida;
    }

    public void setValorBebida(BigDecimal valorBebida) {
        this.valorBebida = valorBebida;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getRegistro() {
        return registro;
    }

    public void setRegistro(LocalDateTime registro) {
        this.registro = registro;
    }
}
