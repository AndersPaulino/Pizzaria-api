package com.pizzaria.app.service;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.entity.Tamanho;
import com.pizzaria.app.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
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

    public String cadastrar(Pizza pizza) {
        validarPizza(pizza);
        pizzaRepository.save(pizza);
        return "Registro cadastrado com sucesso!";
    }

    private void validarPizza(Pizza pizza) {
        List<String> errors = new ArrayList<>();
        Tamanho tamanho = pizza.getTamanho();

        if (tamanho == null) {
            errors.add("O tamanho da pizza deve ser fornecido.");
        } else {
            List<Sabor> sabores = pizza.getSabor();

            if (tamanho == Tamanho.PEQUENA){
                pizza.setQtdeSabor(1);
            }
            if (tamanho == Tamanho.MEDIA){
                pizza.setQtdeSabor(2);
            }
            if (tamanho == Tamanho.GRANDE){
                pizza.setQtdeSabor(3);
            }
            if (tamanho == Tamanho.FAMILIA){
                pizza.setQtdeSabor(4);
            }
            pizza.setQtdeSabor(sabores.size());
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }

    public Pizza atualizarPizza(Long id, Pizza pizzaAtualizada) {
        validarPizza(pizzaAtualizada);

        Pizza pizzaExistente = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("A pizza com o ID " + id + " não existe."));

        // Atualize os campos da pizza existente com os valores da pizza atualizada
        pizzaExistente.setTamanho(pizzaAtualizada.getTamanho());
        pizzaExistente.setSabor(pizzaAtualizada.getSabor());
        pizzaExistente.setValorPizza(pizzaAtualizada.getValorPizza());

        return pizzaRepository.save(pizzaExistente);
    }
    public void deletarPizza(Long id) {
        System.out.println("Deletando pizza com ID: " + id); // Adicione esta linha para depuração

        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("A pizza com o ID " + id + " não existe."));

        pizzaRepository.delete(pizza);
    }
}