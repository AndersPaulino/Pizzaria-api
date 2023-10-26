package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.entity.Venda;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class VendaDTO {
    private Long id;
    private boolean ativo;
    private LocalDateTime registro;
    private List<Cliente> cliente = new ArrayList<>();;
    private List<Funcionario> funcionario = new ArrayList<>();
    private List<Produto> produto = new ArrayList<>();
    private boolean emitirNota;
    private boolean entregar;
    private BigDecimal valorVenda;

    public VendaDTO(){}

    public Venda toVenda(){
        Venda venda = new Venda();
        venda.setId(id);
        venda.setAtivo(ativo);
        venda.setRegistro(registro);
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setProduto(produto);
        venda.setEmitirNota(emitirNota);
        venda.setEntregar(entregar);
        venda.setValorVenda(valorVenda);
        return venda;
    }

    public VendaDTO(Venda venda){
        id = venda.getId();
        ativo = venda.isAtivo();
        registro = venda.getRegistro();
        cliente = venda.getCliente();
        funcionario = venda.getFuncionario();
        produto = venda.getProduto();
        emitirNota = venda.isEmitirNota();
        entregar = venda.isEntregar();
    }

    public static VendaDTO fromVenda(Venda venda){return new VendaDTO(venda);}
}

