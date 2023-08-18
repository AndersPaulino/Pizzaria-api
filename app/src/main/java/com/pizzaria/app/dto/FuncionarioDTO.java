package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Funcionario;

public class FuncionarioDTO {

    private Long id;
    private String nome;

    public FuncionarioDTO() {}
    public FuncionarioDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
