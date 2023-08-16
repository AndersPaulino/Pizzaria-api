package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

public class EnderecoDTO {
    private Long id;
    private String bairro;
    private String rua;
    private int numero;

    public EnderecoDTO(Long id, String bairro, String rua, int numero) {
        this.id = id;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }
    public EnderecoDTO(Endereco endereco){
        id = endereco.getId();
        bairro = endereco.getBairro();
        rua = endereco.getRua();
        numero = endereco.getNumero();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
