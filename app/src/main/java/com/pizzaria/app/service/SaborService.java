package com.pizzaria.app.service;

import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaborService {

    private final SaborRepository saborRepository;

    @Autowired
    public SaborService(SaborRepository saborRepository) {
        this.saborRepository = saborRepository;
    }

    public SaborDTO findById(Long id) {
        Sabor entity = saborRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Sabor com ID " + id + " não encontrado."));
        return new SaborDTO(entity);
    }

    public List<SaborDTO> findAll() {
        List<Sabor> sabores = saborRepository.findAll();
        return sabores.stream().map(SaborDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Sabor cadastrar(Sabor sabor) {
        return saborRepository.save(sabor);
    }

    @Transactional
    public SaborDTO atualizar(SaborDTO saborDTO) {
        Sabor saborExistente = saborRepository.findById(saborDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Sabor not found with ID: " + saborDTO.getId()));

        saborExistente.setNomeSabor(saborDTO.getNomeSabor());
        Sabor saborAtualizado = saborRepository.save(saborExistente);
        return new SaborDTO(saborAtualizado);
    }


    @Transactional
    public void deletar(Long id) {
        saborRepository.deleteById(id);
    }
}
