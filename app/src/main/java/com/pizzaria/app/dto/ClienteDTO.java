package com.pizzaria.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String cpf;

    @JsonProperty("endereco")
    private EnderecoDTO endereco;

    public ClienteDTO(Long id, String nome, String telefone, String cpf, EnderecoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public ClienteDTO(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        telefone = cliente.getTelefone();
        cpf = cliente.getCpf();
    }
}
