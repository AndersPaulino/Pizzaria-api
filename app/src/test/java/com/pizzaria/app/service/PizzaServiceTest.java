package com.pizzaria.app.service.tests;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Tamanho;
import com.pizzaria.app.repository.PizzaRepository;
import com.pizzaria.app.service.ClienteService;
import com.pizzaria.app.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {

        Pizza pizza = new Pizza();
        pizza.setId(1L);
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));


        PizzaDTO pizzaDTO = pizzaService.findById(1L);

        assertNotNull(pizzaDTO);
        assertEquals(1L, pizzaDTO.getId());
    }

    @Test
    void testFindAll() {

        Pizza pizza1 = new Pizza();
        pizza1.setId(1L);

        Pizza pizza2 = new Pizza();
        pizza2.setId(2L);

        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza1);
        pizzas.add(pizza2);


        when(pizzaRepository.findAll()).thenReturn(pizzas);


        List<PizzaDTO> pizzaDTOs = pizzaService.findAll();


        assertNotNull(pizzaDTOs);
        assertEquals(2, pizzaDTOs.size());
        assertEquals(1L, pizzaDTOs.get(0).getId());
        assertEquals(2L, pizzaDTOs.get(1).getId());
    }


    @Test
    void testAtualizarPizza() {
        // Crie uma instância de Pizza fictícia para usar no teste
        Long pizzaId = 1L;
        Pizza pizzaExistente = new Pizza();
        pizzaExistente.setId(pizzaId);
        pizzaExistente.setTamanho(Tamanho.GRANDE);
        pizzaExistente.setValorPizza(BigDecimal.valueOf(20.0));

        // Crie uma instância de PizzaDTO com os dados atualizados
        PizzaDTO pizzaAtualizada = new PizzaDTO();
        pizzaAtualizada.setTamanho(Tamanho.MEDIA);
        pizzaAtualizada.setValorPizza(BigDecimal.valueOf(15.0));

        // Configure o comportamento simulado do repositório
        when(pizzaRepository.findById(pizzaId)).thenReturn(Optional.of(pizzaExistente));
        when(pizzaRepository.save(Mockito.any(Pizza.class))).then(AdditionalAnswers.returnsFirstArg());

        // Chame o método a ser testado
        PizzaDTO pizzaDTOAtualizada = PizzaDTO.fromPizza(pizzaService.atualizarPizza(pizzaId, pizzaAtualizada.toPizza()));

        // Verifique se o método save do repositório foi chamado com a pizza correta
        verify(pizzaRepository, times(1)).save(Mockito.any(Pizza.class));

        // Verifique se os dados da pizza atualizada correspondem ao esperado
        assertEquals(Tamanho.MEDIA, pizzaDTOAtualizada.getTamanho());
        assertEquals(BigDecimal.valueOf(15.0), pizzaDTOAtualizada.getValorPizza());
    }

    @Test
    void testDeletarPizza() {
        // Crie um objeto Pizza fictício para usar no teste
        Long pizzaId = 1L;
        Pizza pizza = new Pizza();
        pizza.setId(pizzaId);

        // Configure o comportamento simulado do repositório
        when(pizzaRepository.findById(pizzaId)).thenReturn(Optional.of(pizza));

        // Chame o método a ser testado
        pizzaService.deletarPizza(pizzaId);

        // Verifique se o método delete do repositório foi chamado com o objeto Pizza correto
        verify(pizzaRepository, times(1)).delete(pizza);
    }
}

