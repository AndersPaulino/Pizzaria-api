package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.BebidaController;
import com.pizzaria.app.controller.ClienteController;
import com.pizzaria.app.controller.SaborController;
import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.service.BebidaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(BebidaController.class)
//@AutoConfigureMockMvc
public class BebidaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Mock
    private BebidaService bebidaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        BebidaController bebidaController = new BebidaController(bebidaService);
        mockMvc = MockMvcBuilders.standaloneSetup(bebidaController).build();
        objectMapper = new ObjectMapper(); // Initialize objectMapper here
    }

    @Test
    public void testFindById() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO(1L, "Coca-Cola", BigDecimal.valueOf(5.0), true, LocalDateTime.now());
        when(bebidaService.findById(1L)).thenReturn(bebidaDTO);

        mockMvc.perform(get("/api/bebida/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeBebida").value("Coca-Cola"))
                .andExpect(jsonPath("$.valorBebida").value(5.0))
                .andExpect(jsonPath("$.ativo").value(true));
    }

    @Test
    public void testFindAll() throws Exception {
        BebidaDTO bebidaDTO1 = new BebidaDTO(1L, "Coca-Cola", BigDecimal.valueOf(5.0), true, LocalDateTime.now());
        BebidaDTO bebidaDTO2 = new BebidaDTO(2L, "Pepsi", BigDecimal.valueOf(4.5), true, LocalDateTime.now());
        List<BebidaDTO> bebidas = Arrays.asList(bebidaDTO1, bebidaDTO2);
        when(bebidaService.findAll()).thenReturn(bebidas);

        mockMvc.perform(get("/api/bebida")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeBebida").value("Coca-Cola"))
                .andExpect(jsonPath("$[0].valorBebida").value(5.0))
                .andExpect(jsonPath("$[0].ativo").value(true))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nomeBebida").value("Pepsi"))
                .andExpect(jsonPath("$[1].valorBebida").value(4.5))
                .andExpect(jsonPath("$[1].ativo").value(true));
    }

    @Test
    public void testCadastrarBebida() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        bebidaDTO.setId(1L);
        bebidaDTO.setNomeBebida("Cola");

        Bebida mockBebida = new Bebida();
        mockBebida.setId(1L);
        mockBebida.setNomeBebida("Cola");

        when(bebidaService.cadastrar(any(BebidaDTO.class))).thenReturn(new BebidaDTO(mockBebida));

        mockMvc.perform(post("/api/bebida")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bebidaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro cadastrado com sucesso!"));

        verify(bebidaService, times(1)).cadastrar(any(BebidaDTO.class));
    }

    @Test
    public void testAtualizarBebida() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        bebidaDTO.setId(1L);
        bebidaDTO.setNomeBebida("Nova Cola");

        when(bebidaService.atualizarBebida(any(BebidaDTO.class))).thenReturn(bebidaDTO);

        mockMvc.perform(put("/api/bebida/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bebidaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro atualizado com sucesso!"));
    }

    @Test
    public void testDeletarBebida() throws Exception {
        mockMvc.perform(delete("/api/bebida/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro excluído com sucesso!"));

        verify(bebidaService, times(1)).deleteBebida(1L);
    }
}
