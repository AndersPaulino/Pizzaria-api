package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Sabor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class SaborDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private String nomeSabor;

    public SaborDTO(){

    }
    public SaborDTO(Sabor sabor){
        id = sabor.getId();
        ativo = sabor.isAtivo();
        registro = sabor.getRegistro();
        nomeSabor = sabor.getNomeSabor();
    }

    public SaborDTO(Long id, boolean ativo, String nomeSabor) {
        this.id = id;
        this.ativo = ativo;
        this.nomeSabor = nomeSabor;
    }
}
