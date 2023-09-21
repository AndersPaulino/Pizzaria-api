package com.pizzaria.app.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.EnderecoController;
import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnderecoController.class)
@AutoConfigureMockMvc
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnderecoService enderecoService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Configurar comportamento dos mocks conforme necessário
    }

    @Test
    public void testCriarEndereco() throws Exception {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setBairro("Bairro Teste");
        enderecoDTO.setRua("Rua Teste");
        enderecoDTO.setNumero(123);
        enderecoDTO.setId(1L);

        when(enderecoService.criarEndereco(any(EnderecoDTO.class))).thenReturn(enderecoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoDTO)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value("Bairro Teste"));
    }


    @Test
    public void testListarEnderecos() throws Exception {
        List<Endereco> enderecos = new ArrayList<>();
        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setBairro("Bairro 1");
        endereco1.setRua("Rua 1");
        endereco1.setNumero(123);

        Endereco endereco2 = new Endereco();
        endereco2.setId(2L);
        endereco2.setBairro("Bairro 2");
        endereco2.setRua("Rua 2");
        endereco2.setNumero(456);

        enderecos.add(endereco1);
        enderecos.add(endereco2);

        when(enderecoService.listarTodosEnderecos()).thenReturn(enderecos);

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L));
    }

    @Test
    public void testBuscarEnderecoPorBairro() throws Exception {
        String bairro = "Bairro Teste";
        List<Endereco> enderecos = new ArrayList<>();
        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setBairro(bairro);
        endereco1.setRua("Rua 1");
        endereco1.setNumero(123);

        Endereco endereco2 = new Endereco();
        endereco2.setId(2L);
        endereco2.setBairro(bairro);
        endereco2.setRua("Rua 2");
        endereco2.setNumero(456);

        enderecos.add(endereco1);
        enderecos.add(endereco2);

        when(enderecoService.buscarEnderecosPorBairro(bairro)).thenReturn(enderecos);

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/bairro/{bairro}", bairro)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bairro").value(bairro))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].bairro").value(bairro));
    }

    @Test
    public void testBuscarEnderecoPorRua() throws Exception {
        String rua = "Rua Teste";
        List<Endereco> enderecos = new ArrayList<>();
        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setBairro("Bairro 1");
        endereco1.setRua(rua);
        endereco1.setNumero(123);

        Endereco endereco2 = new Endereco();
        endereco2.setId(2L);
        endereco2.setBairro("Bairro 2");
        endereco2.setRua(rua);
        endereco2.setNumero(456);

        enderecos.add(endereco1);
        enderecos.add(endereco2);

        when(enderecoService.buscarEnderecosPorRua(rua)).thenReturn(enderecos);

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/rua/{rua}", rua)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rua").value(rua))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].rua").value(rua));
    }

    @Test
    public void testBuscarEnderecoPorNumero() throws Exception {
        int numero = 123;
        List<Endereco> enderecos = new ArrayList<>();
        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setBairro("Bairro 1");
        endereco1.setRua("Rua 1");
        endereco1.setNumero(numero);

        Endereco endereco2 = new Endereco();
        endereco2.setId(2L);
        endereco2.setBairro("Bairro 2");
        endereco2.setRua("Rua 2");
        endereco2.setNumero(numero);

        enderecos.add(endereco1);
        enderecos.add(endereco2);

        when(enderecoService.buscarEnderecosPorNumero(numero)).thenReturn(enderecos);

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/numero/{numero}", numero)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].numero").value(numero))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].numero").value(numero));
    }

    @Test
    public void testBuscarEnderecoPorId() throws Exception {
        Long enderecoId = 1L;
        Endereco endereco = new Endereco();
        endereco.setId(enderecoId);
        endereco.setBairro("Bairro Teste");
        endereco.setRua("Rua Teste");
        endereco.setNumero(123);

        when(enderecoService.buscarEnderecoPorId(enderecoId)).thenReturn(Optional.of(endereco));

        mockMvc.perform(MockMvcRequestBuilders.get("/enderecos/{id}", enderecoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(enderecoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value("Bairro Teste"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value("Rua Teste"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numero").value(123));
    }

    @Test
    public void testAtualizarEndereco() throws Exception {

        Long enderecoId = 1L;
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setBairro("Novo Bairro");
        enderecoDTO.setRua("Nova Rua");
        enderecoDTO.setNumero(42);

        Endereco enderecoAtualizado = new Endereco();
        enderecoAtualizado.setId(enderecoId);
        enderecoAtualizado.setBairro(enderecoDTO.getBairro());
        enderecoAtualizado.setRua(enderecoDTO.getRua());
        enderecoAtualizado.setNumero(enderecoDTO.getNumero());


        when(enderecoService.atualizarEndereco(eq(enderecoId), any(EnderecoDTO.class)))
                .thenReturn(enderecoAtualizado);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/enderecos/{id}", enderecoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(enderecoDTO)))
                .andExpect(status().isOk())
                .andReturn();


        verify(enderecoService, times(1)).atualizarEndereco(eq(enderecoId), any(EnderecoDTO.class));
        String content = result.getResponse().getContentAsString();
        Endereco enderecoResponse = objectMapper.readValue(content, Endereco.class);
        assertEquals(enderecoId, enderecoResponse.getId());
        assertEquals(enderecoDTO.getBairro(), enderecoResponse.getBairro());
        assertEquals(enderecoDTO.getRua(), enderecoResponse.getRua());
        assertEquals(enderecoDTO.getNumero(), enderecoResponse.getNumero());
    }


    @Test
    public void testDeletarEndereco() throws Exception {
        Long enderecoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/enderecos/{id}", enderecoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
