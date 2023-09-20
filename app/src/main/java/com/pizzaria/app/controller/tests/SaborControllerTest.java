package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.PizzaController;
import com.pizzaria.app.controller.SaborController;
import com.pizzaria.app.dto.SaborDTO;
import com.pizzaria.app.entity.Sabor;
import com.pizzaria.app.service.SaborService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(SaborController.class)
//@AutoConfigureMockMvc
public class SaborControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private SaborService saborService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        SaborController saborController = new SaborController(saborService);
        mockMvc = MockMvcBuilders.standaloneSetup(saborController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testFindById() throws Exception {
        // Create a new SaborDTO with expected values
        SaborDTO expectedSaborDTO = new SaborDTO();
        expectedSaborDTO.setId(1L);
        expectedSaborDTO.setNomeSabor("Calabresa");
        expectedSaborDTO.setAtivo(true);

        when(saborService.findById(1L)).thenReturn(expectedSaborDTO);

        mockMvc.perform(get("/api/sabor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeSabor").value("Calabresa"))
                .andExpect(jsonPath("$.ativo").value(true));
    }


    @Test
    public void testFindAll() throws Exception {
        // Create SaborDTO instances with expected values
        SaborDTO saborDTO1 = new SaborDTO();
        saborDTO1.setId(1L);
        saborDTO1.setNomeSabor("Calabresa");
        saborDTO1.setAtivo(true);

        SaborDTO saborDTO2 = new SaborDTO();
        saborDTO2.setId(2L);
        saborDTO2.setNomeSabor("Mussarela");
        saborDTO2.setAtivo(true);

        List<SaborDTO> sabores = Arrays.asList(saborDTO1, saborDTO2);

        when(saborService.findAll()).thenReturn(sabores);

        mockMvc.perform(get("/api/sabor")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeSabor").value("Calabresa"))
                .andExpect(jsonPath("$[0].ativo").value(true))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nomeSabor").value("Mussarela"))
                .andExpect(jsonPath("$[1].ativo").value(true));
    }

    @Test
    public void testCadastrar() throws Exception {
        // Create a new Sabor instance with expected values
        Sabor sabor = new Sabor();
        sabor.setId(1L);
        sabor.setNomeSabor("Margherita");
        sabor.setAtivo(true);

        when(saborService.cadastrar(any(Sabor.class))).thenReturn(sabor);

        mockMvc.perform(post("/api/sabor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sabor)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro cadastrado com sucesso!"));

        verify(saborService, times(1)).cadastrar(any(Sabor.class));
    }

    @Test
    public void testAtualizar() throws Exception {
        SaborDTO saborDTO = new SaborDTO();
        saborDTO.setId(1L);
        saborDTO.setNomeSabor("Novo Sabor");

        when(saborService.atualizar(any(SaborDTO.class))).thenReturn(saborDTO);

        mockMvc.perform(put("/api/sabor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(saborDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro atualizado com sucesso!"));

        verify(saborService, times(1)).atualizar(any(SaborDTO.class));
    }


    @Test
    public void testDeletar() throws Exception {
        mockMvc.perform(delete("/api/sabor/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro excluído com sucesso!"));

        verify(saborService, times(1)).deletar(1L);
    }
}
