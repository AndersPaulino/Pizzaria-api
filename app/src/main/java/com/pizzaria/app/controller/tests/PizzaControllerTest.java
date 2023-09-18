package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.PizzaController;
import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.service.PizzaService;
import com.pizzaria.app.service.SaborService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PizzaController.class)
public class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @MockBean
    private SaborService saborService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Configurar comportamento dos mocks conforme necessário
    }

    @Test
    public void testFindPizzaById() throws Exception {
        // Configurar comportamento do mock para retornar uma PizzaDTO fictícia
        PizzaDTO pizzaFicticia = new PizzaDTO();
        pizzaFicticia.setId(1L);
        pizzaFicticia.setAtivo(true);
        pizzaFicticia.setRegistro(LocalDateTime.now());
        pizzaFicticia.setSabor(new ArrayList<>());
        pizzaFicticia.setTamanho(null);
        pizzaFicticia.setValorPizza(BigDecimal.valueOf(10.0));

        Mockito.when(pizzaService.findById(1L)).thenReturn(pizzaFicticia);

        mockMvc.perform(MockMvcRequestBuilders.get("/pizza/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ativo").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorPizza").value(10.0));
    }

    @Test
    public void testFindAllPizzas() throws Exception {
        // Configurar comportamento do mock para retornar uma lista de PizzaDTOs fictícias
        List<PizzaDTO> pizzas = new ArrayList<>();

        PizzaDTO pizza1 = new PizzaDTO();
        pizza1.setId(1L);
        pizza1.setAtivo(true);
        pizza1.setRegistro(LocalDateTime.now());
        pizza1.setSabor(new ArrayList<>());
        pizza1.setTamanho(null);
        pizza1.setValorPizza(BigDecimal.valueOf(10.0));

        PizzaDTO pizza2 = new PizzaDTO();
        pizza2.setId(2L);
        pizza2.setAtivo(true);
        pizza2.setRegistro(LocalDateTime.now());
        pizza2.setSabor(new ArrayList<>());
        pizza2.setTamanho(null);
        pizza2.setValorPizza(BigDecimal.valueOf(12.0));

        pizzas.add(pizza1);
        pizzas.add(pizza2);

        Mockito.when(pizzaService.findAll()).thenReturn(pizzas);

        mockMvc.perform(MockMvcRequestBuilders.get("/pizza")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
    }


    @Test
    public void testCadastrarPizza() throws Exception {
        // Criar um objeto SaborDTO fictício para o serviço de Sabor
        SaborDTO saborDTOFicticio = new SaborDTO();
        saborDTOFicticio.setId(1L);
        saborDTOFicticio.setAtivo(true);
        saborDTOFicticio.setRegistro(LocalDateTime.now());
        saborDTOFicticio.setNomeSabor("Sabor Teste");

        // Configurar o comportamento do serviço de Sabor para retornar o objeto fictício
        Mockito.when(saborService.findById(Mockito.anyLong())).thenReturn(saborDTOFicticio);


        // Configurar uma PizzaDTO fictícia para enviar no corpo da requisição
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(1L);
        pizzaDTO.setAtivo(true);
        pizzaDTO.setRegistro(LocalDateTime.now());
        pizzaDTO.setSabor(new ArrayList<>());
        pizzaDTO.setTamanho(null);
        pizzaDTO.setValorPizza(BigDecimal.valueOf(15.0));
        pizzaDTO.setQtdeSabor(0);

        mockMvc.perform(MockMvcRequestBuilders.post("/pizza")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pizzaDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Registro cadastrado com sucesso!")));
    }


    @Test
    public void testDeletarPizza() throws Exception {
        Long pizzaId = 1L;
        Mockito.doNothing().when(pizzaService).deletarPizza(pizzaId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/pizza/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Pizza com ID 1 excluída com sucesso.")));
    }
}
