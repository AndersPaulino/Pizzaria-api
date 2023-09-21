package com.pizzaria.app.service;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BebidaService {

    private final BebidaRepository bebidaRepository;

    @Autowired
    public BebidaService(BebidaRepository bebidaRepository) {
        this.bebidaRepository = bebidaRepository;
    }

    private static final String MENSAGEM_ERRO_ID = "Bebida não encontrada com ID:";

    public BebidaDTO findById(Long id) {
        Bebida bebida = bebidaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(MENSAGEM_ERRO_ID+id));

        return new BebidaDTO(bebida);
    }


    public List<BebidaDTO> findAll() {
        List<Bebida> bebidas = bebidaRepository.findAll();
        return bebidas.stream().map(BebidaDTO::new).toList();
    }
    @Transactional(readOnly = true)
    public List<BebidaDTO> findByAtivo(boolean ativo) {
        List<Bebida> bebidas = bebidaRepository.findByAtivo(ativo);

        List<BebidaDTO> bebidaDTOS = new ArrayList<>();

        for (Bebida bebida : bebidas) {
            BebidaDTO dto = new BebidaDTO(bebida);
            bebidaDTOS.add(dto);
        }
        return bebidaDTOS;
    }

    public List<BebidaDTO> findByName(String nomeBebida) {
        List<Bebida> bebidas = bebidaRepository.findByNomeBebida(nomeBebida);
        return bebidas.stream().map(BebidaDTO::new).toList();
    }


    @Transactional(readOnly = true)
    public List<BebidaDTO> findByDiaRegistro(LocalDate registro) {
        List<Bebida> bebidas = bebidaRepository.findByDiaRegistro(registro);

        List<BebidaDTO> bebidaDTOS = new ArrayList<>();

        for (Bebida bebida : bebidas) {
            BebidaDTO dto = new BebidaDTO(bebida);
            bebidaDTOS.add(dto);
        }
        return bebidaDTOS;
    }

    @Transactional(readOnly = true)
    public List<BebidaDTO> findByDiaAtualizar(LocalDate atualizar) {
        List<Bebida> bebidas = bebidaRepository.findByDiaAtualizar(atualizar);

        List<BebidaDTO> bebidaDTOS = new ArrayList<>();

        for (Bebida bebida : bebidas) {
            BebidaDTO dto = new BebidaDTO(bebida);
            bebidaDTOS.add(dto);
        }
        return bebidaDTOS;
    }

    @Transactional
    public BebidaDTO cadastrar(BebidaDTO bebidaDTO) {
        Bebida bebida = new Bebida();
        atualizarCampos(bebida, bebidaDTO);
        Bebida bebidaCadastrada = bebidaRepository.save(bebida);
        return new BebidaDTO(bebidaCadastrada);
    }

    public BebidaDTO atualizarBebida(Long bebidaId, BebidaDTO bebidaDTO) {
        Bebida bebidaExistente = bebidaRepository.findById(bebidaId)
                .orElseThrow(() -> new IllegalArgumentException(MENSAGEM_ERRO_ID+bebidaId));
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
                .orElseThrow(() -> new IllegalArgumentException(MENSAGEM_ERRO_ID+bebidaId));
        bebidaRepository.delete(bebidaExistente);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void desativar(Long id) {
        Optional<Bebida> bebidaOptional = bebidaRepository.findById(id);

        if (bebidaOptional.isPresent()) {
            Bebida bebida = bebidaOptional.get();
            bebida.setAtivo(false);
        } else {
            throw new IllegalArgumentException("ID de estoque inválido!");
        }
    }
}
