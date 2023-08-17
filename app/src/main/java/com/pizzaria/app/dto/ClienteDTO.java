package com.pizzaria.app.dto;

import lombok.Getter;
import lombok.Setter;

public class ClienteDTO {
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String cpf;

    @Getter @Setter
    private EnderecoDTO endereco;
}
