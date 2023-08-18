package com.pizzaria.app.service;

import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.entity.Tamanho;
import com.pizzaria.app.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    public PizzaDTO findById(Long id) {
        Pizza entity = pizzaRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return new PizzaDTO(entity);
    }

    public List<PizzaDTO> findAll() {
        List<Pizza> pizzass = pizzaRepository.findAll();
        return pizzass.stream().map(PizzaDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Pizza cadastrar(Pizza pizza){
        validarPizza(pizza);
        return pizzaRepository.save(pizza);
    }

    /*@Transactional
    public Pizza atualizar(Pizza pizza) {
        Long pizzaId = pizza.getId();
        if (pizzaId == null) {
            throw new IllegalArgumentException("ID da bebida não pode ser nulo para atualização.");
        }

        Pizza pizzaExistente = pizzaRepository.findById(pizzaId).orElseThrow(() -> new IllegalArgumentException("Bebida com ID " + pizzaId + " não encontrada."));

        atualizarCampos(pizzaExistente, pizza);

        return pizzaRepository.save(pizzaExistente);
    }

    @Transactional
    private void atualizarCampos(Pizza pizzaExistente, Pizza pizza) {

        if (pizza.getSabor() != null) {
            pizzaExistente.setSabor(pizza.getSabor());
        }

    }*/

    public List<String> validarPizza(Pizza pizza) {
        List<String> errors = new ArrayList<>();

        Tamanho tamanho = pizza.getTamanho();
        if (tamanho == null) {
            errors.add("O tamanho da pizza deve ser fornecido.");
        } else {


            List<Sabor> sabores = new ArrayList<>();

            switch (tamanho) {
                case PEQUENA:
                    if (sabores.size() > 1) {
                        errors.add("Uma pizza pequena deve ter no máximo 1 sabor.");
                    }
                    break;
                case MEDIA:
                    if (sabores.size() > 2) {
                        errors.add("Uma pizza média deve ter no máximo 2 sabores.");
                    }
                    break;
                case GRANDE:
                    if (sabores.size() > 3) {
                        errors.add("Uma pizza grande deve ter no máximo 3 sabores.");
                    }
                    break;
                case FAMILIA:
                    if (sabores.size() > 4) {
                        errors.add("Uma pizza grande deve ter no máximo 4 sabores.");
                    }
                    break;

                default:
                    errors.add("Tamanho de pizza inválido.");
            }
            if (pizza.getTamanho() == Tamanho.PEQUENA){
                pizza.setQtdeSabor(1);
            }
            if (pizza.getTamanho() == Tamanho.MEDIA){
                pizza.setQtdeSabor(2);
            }
            if (pizza.getTamanho() == Tamanho.GRANDE){
                pizza.setQtdeSabor(3);
            }
            if (pizza.getTamanho() == Tamanho.FAMILIA){
                pizza.setQtdeSabor(4);
            }
            pizza.setSabor(pizza.getSabor());
        }

        return errors;
    }

}
