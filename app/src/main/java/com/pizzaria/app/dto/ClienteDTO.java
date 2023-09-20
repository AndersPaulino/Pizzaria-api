package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Cliente;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private EnderecoDTO endereco;

    public ClienteDTO() {}

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
    }
}
