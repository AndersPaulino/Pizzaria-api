package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.BebidaController;
import com.pizzaria.app.dto.BebidaDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.service.BebidaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
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
public class BebidaControllerTest {
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
        bebidaDTO = new BebidaDTO(bebida);
        MockitoAnnotations.initMocks(this);
        BebidaController bebidaController = new BebidaController(bebidaService);
        mockMvc = MockMvcBuilders.standaloneSetup(bebidaController).build();
        objectMapper = new ObjectMapper();
        bebidaDTO.setNomeBebida("Coca-Cola");
    }

    @Test
    public void testFindById() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO(1L, "Coca-Cola", BigDecimal.valueOf(5.0), true, LocalDateTime.now());
        when(bebidaService.findById(1L)).thenReturn(bebidaDTO);

        mockMvc.perform(get(BEBIDA_API_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nomeBebida").value("Coca-Cola"))
                .andExpect(jsonPath("$.valorBebida").value(5.0))
                .andExpect(jsonPath("$.ativo").value(true));
    }

    @Test
    public void testFindAll() throws Exception {
        BebidaDTO bebidaDTO1 = new BebidaDTO(1L, "Fanta-Laranja", BigDecimal.valueOf(5.0), true, LocalDateTime.now());
        BebidaDTO bebidaDTO2 = new BebidaDTO(2L, "Pepsi", BigDecimal.valueOf(4.5), true, LocalDateTime.now());
        List<BebidaDTO> bebidas = Arrays.asList(bebidaDTO1, bebidaDTO2);
        when(bebidaService.findAll()).thenReturn(bebidas);

        mockMvc.perform(get(BEBIDA_API_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nomeBebida").value("Fanta-Laranja"))
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

        mockMvc.perform(post(BEBIDA_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bebidaDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Registro cadastrado com sucesso! ID da bebida: 1"));

        verify(bebidaService, times(1)).cadastrar(any(BebidaDTO.class));
    }


    @Test
    public void testFindByName() throws Exception {
        String currentString = "Coca-Cola";

        Mockito.when(bebidaService.findByName(currentString)).thenReturn(bebidaDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bebida/nome/" + currentString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    public void testFindByAtivo() throws Exception {
        List<BebidaDTO> bebidaDTOList = Collections.singletonList(bebidaDTO);
        Mockito.when(bebidaService.findByAtivo(true)).thenReturn(bebidaDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bebida/ativo/true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindByDiaRegistro() throws Exception {
        List<BebidaDTO> bebidaDTOList = Collections.singletonList(bebidaDTO);
        LocalDate currentDate = LocalDate.now();
        Mockito.when(bebidaService.findByDiaRegistro(currentDate)).thenReturn(bebidaDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bebida/registro/dia/" + currentDate)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindByDiaAtualizar() throws Exception {
        List<BebidaDTO> bebidaDTOList = Collections.singletonList(bebidaDTO);
        LocalDate currentDate = LocalDate.now();
        Mockito.when(bebidaService.findByDiaAtualizar(currentDate)).thenReturn(bebidaDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/bebida/atualizar/dia/" + currentDate)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAtualizarBebida() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        bebidaDTO.setId(1L); // Defina o ID da bebida corretamente
        bebidaDTO.setNomeBebida("Nova Cola");

        when(bebidaService.atualizarBebida(eq(1L), any(BebidaDTO.class))).thenReturn(bebidaDTO);

        mockMvc.perform(put(BEBIDA_API_URL + "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bebidaDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro atualizado com sucesso!"));
    }
    
    @Test
    public void testDeletarBebida() throws Exception {
        mockMvc.perform(delete(BEBIDA_API_URL + "1")

                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro excluído com sucesso!"));

        verify(bebidaService, times(1)).deleteBebida(1L);
    }

    @Test
    public void testDesativarBebida() throws Exception {
        mockMvc.perform(delete("/api/bebida/desativar/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro desativado com sucesso!"));

        verify(bebidaService, times(1)).desativar(1L);
    }
}
