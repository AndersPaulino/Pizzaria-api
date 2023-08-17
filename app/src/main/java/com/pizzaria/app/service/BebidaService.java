package com.pizzaria.app.service;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BebidaService {

    private BebidaRepository bebidaRepository;

    @Autowired
    public BebidaService(BebidaRepository bebidaRepository){
        this.bebidaRepository = bebidaRepository;
    }

    public BebidaDTO findById(Long id) {
        Bebida entity = bebidaRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return new BebidaDTO(entity);
    }

    public List<BebidaDTO> findAll() {
        List<Bebida> bebidas = bebidaRepository.findAll();
        return bebidas.stream().map(BebidaDTO::new).collect(Collectors.toList());
    }

    public Bebida cadastrar(Bebida bebida){
       return bebidaRepository.save(bebida);
    }

    @Transactional
    public Bebida atualizar(Bebida bebida) {
        Long bebidaId = bebida.getId();
        if (bebidaId == null) {
            throw new IllegalArgumentException("ID da bebida não pode ser nulo para atualização.");
        }

        Bebida bebidaExistente = bebidaRepository.findById(bebidaId).orElseThrow(() -> new IllegalArgumentException("Bebida com ID " + bebidaId + " não encontrada."));

        atualizarCampos(bebidaExistente, bebida);

        return bebidaRepository.save(bebidaExistente);
    }

    private void atualizarCampos(Bebida bebidaExistente, Bebida bebida) {
        if (bebida.getNomeBebida() != null) {
            bebidaExistente.setNomeBebida(bebida.getNomeBebida());
        }
        if (bebida.getValorBebida() != null) {
            bebidaExistente.setValorBebida(bebida.getValorBebida());
        }
    }
}
