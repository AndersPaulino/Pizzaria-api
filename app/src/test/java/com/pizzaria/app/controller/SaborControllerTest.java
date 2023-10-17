package com.pizzaria.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.service.SaborService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SaborController.class)
class SaborControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SaborService saborService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        SaborController saborController = new SaborController(saborService);
        mockMvc = MockMvcBuilders.standaloneSetup(saborController).build();
        objectMapper = new ObjectMapper();
    }
}
