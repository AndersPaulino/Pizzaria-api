package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Cliente;

import com.pizzaria.app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ClienteDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private String nome;
    private String cpf;
    private List<Endereco> endereco = new ArrayList<>();

    public ClienteDTO(){}
    public  Cliente toCliente(){
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setAtivo(ativo);
        cliente.setRegistro(registro);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEndereco(endereco);
        return cliente;
    }
    public ClienteDTO(Cliente cliente) {
        id = cliente.getId();
        ativo = cliente.isAtivo();
        registro = cliente.getRegistro();
        nome = cliente.getNome();
        cpf = cliente.getCpf();
        endereco = cliente.getEndereco();
    }
    public static ClienteDTO fromCliente(Cliente cliente) {
        return new ClienteDTO(cliente);
    }
}