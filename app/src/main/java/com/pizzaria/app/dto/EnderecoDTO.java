package com.pizzaria.app.dto;

import lombok.Getter;
import lombok.Setter;

public class EnderecoDTO {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String bairro;

    @Getter @Setter
    private String rua;

    @Getter @Setter
    private int numero;
}
