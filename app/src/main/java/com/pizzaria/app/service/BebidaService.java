package com.pizzaria.app.service;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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
       Bebida bebida = bebidaRepository.findById(id).get();
       BebidaDTO bebidaDTO = new BebidaDTO(bebida);
       return bebidaDTO;
    }

    public List<BebidaDTO> findAll() {
        List<Bebida> bebidas = bebidaRepository.findAll();
        return bebidas.stream().map(BebidaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BebidaDTO findByName(String nomeBebida){
        Bebida bebida = new Bebida();
        BebidaDTO bebidaDTO = new BebidaDTO(bebida);
        if (bebidaDTO != null){
            return bebidaDTO;
        } else {
            try {
                throw new ChangeSetPersister.NotFoundException();
            } catch (ChangeSetPersister.NotFoundException e){
                throw  new RuntimeException(e);
            }
        }
    }

    @Transactional
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

    @Transactional
    private void atualizarCampos(Bebida bebidaExistente, Bebida bebida) {
        if (bebida.getNomeBebida() != null) {
            bebidaExistente.setNomeBebida(bebida.getNomeBebida());
        }
        if (bebida.getValorBebida() != null) {
            bebidaExistente.setValorBebida(bebida.getValorBebida());
        }
    }
}
