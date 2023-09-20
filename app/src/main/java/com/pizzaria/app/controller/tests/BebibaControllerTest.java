package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.BebidaController;
import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.service.BebidaService;
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

@WebMvcTest(BebidaController.class)
public class BebibaControllerTest {
    private MockMvc mockMvc;
    @Autowired
    public BebibaControllerTest(MockMvc mockMvc){
        this.mockMvc = mockMvc;
    }
    @MockBean
    private BebidaService bebidaService;

    @Autowired
    private ObjectMapper objectMapper;

    private BebidaDTO bebidaDTO;

    @BeforeEach
    public void setUp() {
        Bebida bebida = new Bebida();
        bebidaDTO = new BebidaDTO(bebidaDTO);
        bebidaDTO.setNomeBebida("Estoque01");
        bebidaDTO.setAtivo(true);
    }

    @Test
    public void testFindById() throws Exception {
        Mockito.when(bebidaService.findById(1L)).thenReturn(bebidaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bebida/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
