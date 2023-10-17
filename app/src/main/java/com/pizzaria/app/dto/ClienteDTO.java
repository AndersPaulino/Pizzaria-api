package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Cliente;

import com.pizzaria.app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ClienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private Endereco endereco;

    public ClienteDTO(Long id, String nome, String cpf, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public ClienteDTO(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        cpf = cliente.getCpf();
        endereco = cliente.getEndereco();
    }
}
