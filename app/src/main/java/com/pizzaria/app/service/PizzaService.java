package com.pizzaria.app.service;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }
    public PizzaDTO findById(Long id){
        Pizza entity = pizzaRepository.findById(id).get();
        PizzaDTO dto = new PizzaDTO(entity);
        return dto;
    }

}
