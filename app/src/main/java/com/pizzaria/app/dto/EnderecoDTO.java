package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class EnderecoDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private LocalDateTime atualizar;
    private String bairro;
    private String rua;
    private int numero;

    public EnderecoDTO(Endereco endereco){
        id = endereco.getId();
        ativo = endereco.isAtivo();
        registro = endereco.getRegistro();
        atualizar = endereco.getAtualizar();
        bairro = endereco.getBairro();
        rua = endereco.getRua();
        numero = endereco.getNumero();
    }
    public EnderecoDTO(Long id, boolean ativo, LocalDateTime registro, LocalDateTime atualizar, String bairro, String rua, int numero) {
        this.id = id;
        this.ativo = ativo;
        this.registro = registro;
        this.atualizar = atualizar;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }
}

