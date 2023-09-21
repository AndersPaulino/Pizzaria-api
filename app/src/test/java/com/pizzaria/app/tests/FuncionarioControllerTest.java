package com.pizzaria.app.controller.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.FuncionarioController;
import com.pizzaria.app.dto.FuncionarioDTO;
import com.pizzaria.app.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FuncionarioController.class)
public class FuncionarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FuncionarioService funcionarioService;

    private static final String FUNCIONARIO_API_URL = "/funcionarios";
    private static final String FUNCIONARIO_API_URL_WITH_ID = "/funcionarios/";
    private static final String FUNCIONARIO_BUSCAR_URL = "/funcionarios/buscar?nome=";
    private static final String NOME_MARCOS = "Marcos";
    private static final String NOME_EDUARDO = "Eduardo";

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testListarTodosFuncionarios() throws Exception {
        FuncionarioDTO funcionarioDTO1 = new FuncionarioDTO();
        funcionarioDTO1.setId(1L);
        funcionarioDTO1.setNome("João");

        FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO();
        funcionarioDTO2.setId(2L);
        funcionarioDTO2.setNome("Maria");

        List<FuncionarioDTO> funcionariosDTO = Arrays.asList(funcionarioDTO1, funcionarioDTO2);

        when(funcionarioService.listarTodosFuncionariosDTO()).thenReturn(funcionariosDTO);

        mockMvc.perform(get(FUNCIONARIO_API_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Maria"));
    }

    @Test
    public void testBuscarFuncionarioPorIdExistente() throws Exception {
        // Crie um FuncionarioDTO para simular o retorno do serviço
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(1L);
        funcionarioDTO.setNome("Marcio");

        when(funcionarioService.buscarFuncionarioPorIdDTO(1L)).thenReturn(Optional.of(funcionarioDTO));

        mockMvc.perform(get(FUNCIONARIO_API_URL_WITH_ID + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Marcio"));
    }

    @Test
    public void testBuscarFuncionarioPorIdInexistente() throws Exception {
        // Configure o comportamento do mock do serviço para retornar Optional vazio
        when(funcionarioService.buscarFuncionarioPorIdDTO(1L)).thenReturn(Optional.empty());

        // Realize a solicitação GET para /funcionarios/1 e espere um erro 404
        mockMvc.perform(get(FUNCIONARIO_API_URL_WITH_ID + "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testBuscarFuncionariosPorNome() throws Exception {
        // Crie uma lista de FuncionarioDTOs para simular o retorno do serviço
        FuncionarioDTO funcionarioDTO1 = new FuncionarioDTO();
        funcionarioDTO1.setId(1L);
        funcionarioDTO1.setNome(NOME_MARCOS);

        FuncionarioDTO funcionarioDTO2 = new FuncionarioDTO();
        funcionarioDTO2.setId(2L);
        funcionarioDTO2.setNome("Vitoria");

        List<FuncionarioDTO> funcionariosDTO = Arrays.asList(funcionarioDTO1, funcionarioDTO2);

        when(funcionarioService.buscarFuncionariosPorNomeDTO(NOME_MARCOS)).thenReturn(funcionariosDTO);

        // Realize a solicitação GET para /funcionarios/buscar?nome=João
        mockMvc.perform(get(FUNCIONARIO_BUSCAR_URL+NOME_MARCOS)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value(NOME_MARCOS))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Vitoria"));
    }

    @Test
    public void testCadastrarFuncionario() throws Exception {
        // Configurar um FuncionarioDTO fictício para enviar no corpo da requisição
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId(1L);
        funcionarioDTO.setNome(NOME_EDUARDO);

        when(funcionarioService.cadastrarFuncionario(argThat(dto -> dto.getNome().equals(NOME_EDUARDO))))
                .thenReturn(funcionarioDTO);
        mockMvc.perform(MockMvcRequestBuilders.post(FUNCIONARIO_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(NOME_EDUARDO));
    }

    @Test
    public void testAtualizarFuncionario() throws Exception {
        // Dados de teste
        Long funcionarioId = 1L;
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setNome("Novo Nome");

        // Simulando o serviço
        when(funcionarioService.atualizarFuncionario(eq(funcionarioId), any(FuncionarioDTO.class)))
                .thenAnswer(invocation -> {
                    FuncionarioDTO dto = invocation.getArgument(1);
                    dto.setId(funcionarioId); // Definir o ID simulado
                    return dto;
                });

        // Chamada ao endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put(FUNCIONARIO_API_URL_WITH_ID + funcionarioId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(funcionarioDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // Verificações
        verify(funcionarioService, times(1)).atualizarFuncionario(eq(funcionarioId), any(FuncionarioDTO.class));
        String content = result.getResponse().getContentAsString();
        FuncionarioDTO funcionarioResponse = objectMapper.readValue(content, FuncionarioDTO.class);
        assertEquals(funcionarioId, funcionarioResponse.getId());
        assertEquals(funcionarioDTO.getNome(), funcionarioResponse.getNome());
    }

    @Test
    public void testDeletarFuncionario() throws Exception {
        // Realize a solicitação DELETE para /funcionarios/1
        mockMvc.perform(delete(FUNCIONARIO_API_URL_WITH_ID+"1"))
                .andExpect(status().isNoContent());
    }
}
