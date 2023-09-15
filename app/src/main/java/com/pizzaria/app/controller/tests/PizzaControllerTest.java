package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.PizzaController;
import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Tamanho;
import com.pizzaria.app.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PizzaController.class)
@AutoConfigureMockMvc
public class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaRepository pizzaRepository;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testFindById() throws Exception {
        // Crie uma instância de PizzaDTO para simular o retorno do serviço
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setId(1L);
        pizzaDTO.setTamanho(Tamanho.MEDIA);

        // Configure o comportamento do mock do serviço para retornar a pizza pelo ID
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizzaDTO.toPizza()));

        // Realize a solicitação GET para /pizza/1
        mockMvc.perform(get("/pizza/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testFindAll() throws Exception {
        // Crie uma lista de PizzaDTOs para simular o retorno do serviço
        PizzaDTO pizzaDTO1 = new PizzaDTO();
        pizzaDTO1.setId(1L);
        pizzaDTO1.setTamanho(Tamanho.PEQUENA);

        PizzaDTO pizzaDTO2 = new PizzaDTO();
        pizzaDTO2.setId(2L);
        pizzaDTO2.setTamanho(Tamanho.MEDIA);

        List<PizzaDTO> pizzaDTOs = Arrays.asList(pizzaDTO1, pizzaDTO2);

        // Configure o comportamento do mock do serviço para retornar a lista de pizzas
        when(pizzaRepository.findAll()).thenReturn(Arrays.asList(pizzaDTO1.toPizza(), pizzaDTO2.toPizza()));

        // Realize a solicitação GET para /pizza
        mockMvc.perform(get("/pizza")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].tamanho").value("PEQUENA"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].tamanho").value("MEDIA"));
    }

    @Test
    public void testCadastrarPizza() throws Exception {
        // Crie uma instância de PizzaDTO para simular a entrada do usuário
        PizzaDTO pizzaDTO = new PizzaDTO();
        pizzaDTO.setTamanho(Tamanho.MEDIA);

        // Configure o comportamento do mock do repositório para salvar a pizza
        when(pizzaRepository.save(any())).thenReturn(pizzaDTO.toPizza());

        // Realize a solicitação POST para /pizza com o corpo JSON
        mockMvc.perform(post("/pizza")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pizzaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.tamanho").value("MEDIA"));

        // Verifique se o método save do repositório foi chamado
        verify(pizzaRepository, times(1)).save(any());
    }

    @Test
    public void testDeletarPizza() throws Exception {
        // Configure o comportamento do mock do repositório para retornar a pizza quando for solicitada por ID
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(new Pizza()));

        // Realize a solicitação DELETE para /pizza/1
        mockMvc.perform(delete("/pizza/1"))
                .andExpect(status().isOk());

        // Verifique se o método deleteById do repositório foi chamado
        verify(pizzaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletarPizzaInexistente() throws Exception {
        // Configure o comportamento do mock do repositório para retornar Optional vazio
        when(pizzaRepository.findById(1L)).thenReturn(Optional.empty());

        // Realize a solicitação DELETE para /pizza/1 e espere um erro 404
        mockMvc.perform(delete("/pizza/1"))
                .andExpect(status().isNotFound());

        // Verifique se o método deleteById do repositório não foi chamado
        verify(pizzaRepository, never()).deleteById(1L);
    }
}
