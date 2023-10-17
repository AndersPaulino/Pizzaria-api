package com.pizzaria.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.service.PizzaService;
import com.pizzaria.app.service.SaborService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PizzaController.class)
@AutoConfigureMockMvc
class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @MockBean
    private SaborService saborService;

    @Autowired
    private ObjectMapper objectMapper;

    private PizzaDTO pizzaDTO;


    @BeforeEach
    void setUp() {
        Pizza pizza = new Pizza();
        pizzaDTO = new PizzaDTO(pizza);
    }

}
