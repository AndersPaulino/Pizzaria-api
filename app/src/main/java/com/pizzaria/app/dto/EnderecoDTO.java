package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EnderecoDTO {
    private Long id;
    private String bairro;
    private String rua;
    private int numero;

    public EnderecoDTO() {}

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.bairro = endereco.getBairro();
        this.rua = endereco.getRua();
        this.numero = endereco.getNumero();
    }
}

