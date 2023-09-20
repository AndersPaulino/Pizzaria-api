package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Sabor;

import java.time.LocalDateTime;

public class SaborDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private String nomeSabor;

    public SaborDTO(){

    }
    public SaborDTO(Sabor sabor){
        id = sabor.getId();
        ativo = sabor.isAtivo();
        registro = sabor.getRegistro();
        nomeSabor = sabor.getNomeSabor();
    }

    public SaborDTO(Long id, boolean ativo, String nomeSabor) {
        this.id = id;
        this.ativo = ativo;
        this.nomeSabor = nomeSabor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNomeSabor() {
        return nomeSabor;
    }

    public void setNomeSabor(String nomeSabor) {
        this.nomeSabor = nomeSabor;
    }
}
