package com.pizzaria.app.service;

import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.repository.SaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaborService {

    private SaborRepository saborRepository;

    @Autowired
    public SaborService(SaborRepository saborRepository){
        this.saborRepository = saborRepository;
    }

    public SaborDTO findById(Long id){
        Sabor entity = saborRepository.findById(id).get();
        SaborDTO dto = new SaborDTO(entity);
        return dto;
    }
}
