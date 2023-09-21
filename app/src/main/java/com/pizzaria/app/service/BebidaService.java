package com.pizzaria.app.service;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BebidaService {

    private final BebidaRepository bebidaRepository;

    @Autowired
    public BebidaService(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    public BebidaDTO findById(Long id) {
        Bebida bebida = bebidaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bebida não encontrada com ID: " + id));
        return new BebidaDTO(bebida);
    }

    public List<BebidaDTO> findAll() {
        List<Bebida> bebidas = bebidaRepository.findAll();
        return bebidas.stream().map(BebidaDTO::new).collect(Collectors.toList());
    }

    public List<BebidaDTO> findByName(String nomeBebida) {
        List<Bebida> bebidas = bebidaRepository.findByNomeBebida(nomeBebida);
        return bebidas.stream().map(BebidaDTO::new).collect(Collectors.toList());
    }

    public BebidaDTO cadastrar(BebidaDTO bebidaDTO) {
        Bebida bebida = new Bebida();
        atualizarCampos(bebida, bebidaDTO);
        Bebida bebidaCadastrada = bebidaRepository.save(bebida);
        return new BebidaDTO(bebidaCadastrada);
    }

    public BebidaDTO atualizarBebida(Long bebidaId, BebidaDTO bebidaDTO) {
        Bebida bebidaExistente = bebidaRepository.findById(bebidaId)
                .orElseThrow(() -> new IllegalArgumentException("Bebida não encontrada com ID: " + bebidaId));
        atualizarCampos(bebidaExistente, bebidaDTO);
        Bebida bebidaAtualizada = bebidaRepository.save(bebidaExistente);
        return new BebidaDTO(bebidaAtualizada);
    }

    private void atualizarCampos(Bebida bebida, BebidaDTO bebidaDTO) {
        if (bebidaDTO.getNomeBebida() != null) {
            bebida.setNomeBebida(bebidaDTO.getNomeBebida());
        }
        if (bebidaDTO.getValorBebida() != null) {
            bebida.setValorBebida(bebidaDTO.getValorBebida());
        }
    }

    public void deleteBebida(Long bebidaId) {
        Bebida bebidaExistente = bebidaRepository.findById(bebidaId)
                .orElseThrow(() -> new IllegalArgumentException("Bebida não encontrada com ID: " + bebidaId));
        bebidaRepository.delete(bebidaExistente);
    }
}
