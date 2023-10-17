package com.pizzaria.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.service.BebidaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BebidaController.class)
class BebidaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BebidaService bebidaService;

    private BebidaDTO bebidaDTO;

    @BeforeEach
    void setUp() {
        Bebida bebida = new Bebida();
        bebidaDTO = new BebidaDTO(bebida);
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
        bebidaDTO.setNomeBebida("Coca-Cola");
        bebidaDTO.setValorBebida(BigDecimal.valueOf(5.0));
        bebidaDTO.setAtivo(true);
        bebidaDTO.setRegistro(LocalDateTime.now());
        bebidaDTO.setId(1L);
    }

}
