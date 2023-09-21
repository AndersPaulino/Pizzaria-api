package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Funcionario;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FuncionarioDTO {

    private Long id;
    private String nome;

    public FuncionarioDTO() {}
    public FuncionarioDTO(Funcionario funcionario) {
        this.id = funcionario.getId();
        this.nome = funcionario.getNome();
    }
}
