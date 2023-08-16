package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente",schema = "public")
public class Cliente extends AbstractEntity{

    @Getter @Setter
    private String nome;

    @Getter @Setter
    private String telefone;

    @Getter @Setter
    private String cpf;

    @Getter @Setter
   private String endereco;
    
    public Cliente() {}
    public Cliente(String nome, String telefone, String cpf, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }
}
