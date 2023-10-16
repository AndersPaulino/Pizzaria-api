package com.pizzaria.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.service.ClienteService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService clienteService;

    private ClienteDTO clienteDTO;

    @BeforeEach
    public void setUp(){
        Cliente cliente = new Cliente();
        clienteDTO = new ClienteDTO(cliente);
    }
    @Test
    void testCriarCliente() throws Exception {
        // Dados de teste
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("João");
        clienteDTO.setCpf("1234567890");

        Cliente clienteCriado = new Cliente();
        clienteCriado.setId(1L);
        clienteCriado.setNome(clienteDTO.getNome());
        clienteCriado.setCpf(clienteDTO.getCpf());

        // Configurar simulação do serviço
        when(clienteService.criarCliente(any(ClienteDTO.class))).thenReturn(clienteDTO);

        // Executar a requisição POST
        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(clienteDTO.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(clienteDTO.getNome()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf").value(clienteDTO.getCpf()));
    }

    @Test
    void testListarTodosClientes() throws Exception {
        // Dados de teste
        ClienteDTO cliente1 = new ClienteDTO();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setCpf("1234567890");

        ClienteDTO cliente2 = new ClienteDTO();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
        cliente2.setCpf("9876543210");

        List<ClienteDTO> clientesDTO = Arrays.asList(cliente1, cliente2);

        // Configurar simulação do serviço
        when(clienteService.listarTodosClientesDTO()).thenReturn(clientesDTO);

        // Executar a requisição GET
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(clientesDTO.size()));
    }


    @Test
    void testBuscarClientesPorNome() throws Exception {
        // Dados de teste
        String nome = "Cliente 1";
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome(nome);
        cliente1.setCpf("1234567890");

        List<Cliente> clientesEncontrados = Arrays.asList(cliente1);

        // Configurar simulação do serviço
        when(clienteService.buscarClientesPorNome(nome)).thenReturn(clientesEncontrados);

        // Executar a requisição GET
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/nome/{nome}", nome)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(clientesEncontrados.size()));
    }

    @Test
    void testBuscarClientesPorCpf() throws Exception {
        // Dados de teste
        String cpf = "1234567890";
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setCpf(cpf);

        List<Cliente> clientesEncontrados = Arrays.asList(cliente1);

        // Configurar simulação do serviço
        when(clienteService.buscarClientesPorCpf(cpf)).thenReturn(clientesEncontrados);

        // Executar a requisição GET
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/cpf/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(clientesEncontrados.size()));
    }

    @Test
    void testBuscarClientePorId() throws Exception {
        // Dados de teste
        Long clienteId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        cliente.setNome("Nome do Cliente");
        cliente.setCpf("1234567890");

        // Configurar o comportamento do serviço para retornar o cliente ao buscar por ID
        when(clienteService.buscarClientePorId(clienteId)).thenReturn(Optional.of(cliente));

        // Executar a requisição GET para buscar o cliente pelo ID
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Verificações
        verify(clienteService).buscarClientePorId(clienteId);
        String content = result.getResponse().getContentAsString();
        Cliente clienteResponse = objectMapper.readValue(content, Cliente.class);
        assertEquals(clienteId, clienteResponse.getId());
        assertEquals("Nome do Cliente", clienteResponse.getNome());
        assertEquals("1234567890", clienteResponse.getCpf());
    }

    @Test
    void testBuscarClientePorIdClienteNaoEncontrado() throws Exception {
        Long clienteId = 1L;

        // Simular o serviço retornando um cliente vazio (cliente não encontrado)
        when(clienteService.buscarClientePorId(clienteId)).thenReturn(Optional.empty());

        // Executar a requisição GET para buscar o cliente pelo ID
        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/{id}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }





    @Test
    void testAtualizarCliente() throws Exception {
        // Dados de teste
        Long clienteId = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Novo Nome");
        clienteDTO.setCpf("9876543210");

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(clienteId);
        clienteAtualizado.setNome(clienteDTO.getNome());
        clienteAtualizado.setCpf(clienteDTO.getCpf());

        // Simulando o serviço
        when(clienteService.atualizarCliente(eq(clienteId), any(ClienteDTO.class)))
                .thenReturn(clienteAtualizado);

        // Chamada ao endpoint
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .put("/clientes/{id}", clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andReturn();

        // Verificações
        verify(clienteService).atualizarCliente(eq(clienteId), any(ClienteDTO.class));
        String content = result.getResponse().getContentAsString();
        Cliente clienteResponse = objectMapper.readValue(content, Cliente.class);
        assertEquals(clienteId, clienteResponse.getId());
        assertEquals(clienteDTO.getNome(), clienteResponse.getNome());
        assertEquals(clienteDTO.getCpf(), clienteResponse.getCpf());
    }

    @Test
    void testDeletarCliente() throws Exception {
        // Dados de teste
        Long id = 1L;

        // Executar a requisição DELETE
        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
