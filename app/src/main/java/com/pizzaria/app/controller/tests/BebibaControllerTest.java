package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.BebidaController;
import com.pizzaria.app.controller.ClienteController;
import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.service.BebidaService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

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
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BebidaService bebidaService;

    private BebidaDTO bebidaDTO;

    @BeforeEach
    public void setUp() {
        Bebida bebida = new Bebida();
        BebidaDTO bebidaDTO = new BebidaDTO(bebida);
        bebidaDTO.setNomeBebida("COCA-COLA");
        bebidaDTO.setValorBebida(BigDecimal.valueOf(5));
    }

    @Test
    public void testFindById() throws Exception {
        Mockito.when(bebidaService.findById(1L)).thenReturn(bebidaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bebida/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
