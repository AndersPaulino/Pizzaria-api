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

    private BebidaRepository bebidaRepository;

    @Autowired
    public BebidaService(BebidaRepository bebidaRepository){
        this.bebidaRepository = bebidaRepository;
    }

    public BebidaDTO findById(Long id){
        Bebida entity = bebidaRepository.findById(id).get();
        BebidaDTO dto = new BebidaDTO(entity);
        return dto;
    }
}
