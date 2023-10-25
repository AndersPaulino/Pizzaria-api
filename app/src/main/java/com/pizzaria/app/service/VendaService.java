package com.pizzaria.app.service;


import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.entity.Venda;
import com.pizzaria.app.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository){
        this.vendaRepository = vendaRepository;
    }


    @Transactional(readOnly = true)
    public Optional<Venda> findById(Long id) {
        return vendaRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<VendaDTO> findAll(){
        List<Venda> vendas = vendaRepository.findAll();
        return vendas.stream()
                .map(VendaDTO::new)
                .toList();
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Venda venda){
        vendaRepository.save(venda);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Venda venda){
        Optional<Venda> vendaOptional = vendaRepository.findById(id);
        Venda venda1 = vendaOptional.get();
        if (vendaOptional.isPresent()){
            vendaRepository.save(venda1);
        } else {
            throw new IllegalArgumentException("Id da venda não econtrado!");
        }
    }

    public void deleteVenda(Long id){
        Venda venda = vendaRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Venda não econtrada com o ID: " + id));
        vendaRepository.delete(venda);
    }
    public void desativar(Long id){
        Optional<Venda> vendaOptional = vendaRepository.findById(id);
        Venda venda = vendaOptional.get();

        if (vendaOptional.isPresent()){
            venda.setAtivo(false);
            vendaRepository.save(venda);
            throw new IllegalArgumentException("Venda desativada com sucesso!");
        } else {
            throw new IllegalArgumentException("ID da venda inválido!");
        }
    }
}

