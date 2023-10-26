package com.pizzaria.app.service;


import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.entity.Venda;
import com.pizzaria.app.repository.VendaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    private VendaRepository vendaRepository;


    public VendaService(VendaRepository vendaRepository){
        this.vendaRepository = vendaRepository;
    }


    @Transactional(readOnly = true)
    public VendaDTO findById(Long id) {
        Venda entity = vendaRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return new VendaDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<VendaDTO> findAll() {
        List<Venda> vendas = vendaRepository.findAll();
        return vendas.stream().map(VendaDTO::new).collect(Collectors.toList());
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrarVenda(Venda venda){
        vendaRepository.save(venda);
    }

    public Venda atualizarVenda(Long id, Venda vendaAtualizada){
        Venda vendaExistente = vendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("A Venda com o ID " + id + " não existe."));

        vendaExistente.setCliente(vendaAtualizada.getCliente());
        vendaExistente.setFuncionario(vendaAtualizada.getFuncionario());
        vendaExistente.setProduto(vendaAtualizada.getProduto());
        vendaExistente.setEmitirNota(vendaAtualizada.isEmitirNota());
        vendaExistente.setEntregar(vendaAtualizada.isEntregar());
        vendaExistente.setValorVenda(vendaAtualizada.getValorVenda());

        return vendaRepository.save(vendaExistente);
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

