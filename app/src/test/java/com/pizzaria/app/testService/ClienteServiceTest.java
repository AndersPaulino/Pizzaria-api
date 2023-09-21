package com.pizzaria.app.testService;

import com.pizzaria.app.controller.BebidaController;
import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.ClienteRepository;
import com.pizzaria.app.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ClienteService.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("João");
        clienteDTO.setCpf("1234567890");

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setBairro("Bairro Teste");
        enderecoDTO.setRua("Rua Teste");
        enderecoDTO.setNumero(42);

        clienteDTO.setEndereco(enderecoDTO);

        Cliente clienteCriado = new Cliente();
        clienteCriado.setId(1L);
        clienteCriado.setNome(clienteDTO.getNome());
        clienteCriado.setCpf(clienteDTO.getCpf());

        Endereco endereco = new Endereco();
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());

        clienteCriado.setEndereco(endereco);

        when(clienteRepository.save(any(Cliente.class))).thenAnswer(invocation -> {
            Cliente clienteSalvo = invocation.getArgument(0);
            clienteSalvo.setId(1L);
            return clienteSalvo;
        });

        ClienteDTO clienteCriadoDTO = clienteService.criarCliente(clienteDTO);

        // Verificações
        assertNotNull(clienteCriadoDTO);
        assertEquals(clienteCriado.getId(), clienteCriadoDTO.getId());
        assertEquals(clienteCriado.getNome(), clienteCriadoDTO.getNome());
        assertEquals(clienteCriado.getCpf(), clienteCriadoDTO.getCpf());
        assertNotNull(clienteCriadoDTO.getEndereco());
        assertEquals(enderecoDTO.getBairro(), clienteCriadoDTO.getEndereco().getBairro());
        assertEquals(enderecoDTO.getRua(), clienteCriadoDTO.getEndereco().getRua());
        assertEquals(enderecoDTO.getNumero(), clienteCriadoDTO.getEndereco().getNumero());
    }


    @Test
    public void testListarTodosClientesDTO() {
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setCpf("123");
        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Bairro 1");
        endereco1.setRua("Rua 1");
        endereco1.setNumero(1);
        cliente1.setEndereco(endereco1);
        clientes.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
        cliente2.setCpf("456");
        Endereco endereco2 = new Endereco();
        endereco2.setBairro("Bairro 2");
        endereco2.setRua("Rua 2");
        endereco2.setNumero(2);
        cliente2.setEndereco(endereco2);
        clientes.add(cliente2);

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<ClienteDTO> clienteDTOs = clienteService.listarTodosClientesDTO();

        // Verificações
        assertNotNull(clienteDTOs);
        assertEquals(2, clienteDTOs.size());

        ClienteDTO clienteDTO1 = clienteDTOs.get(0);
        assertEquals(cliente1.getId(), clienteDTO1.getId());
        assertEquals(cliente1.getNome(), clienteDTO1.getNome());
        assertEquals(cliente1.getCpf(), clienteDTO1.getCpf());
        assertNotNull(clienteDTO1.getEndereco());
        assertEquals(cliente1.getEndereco().getBairro(), clienteDTO1.getEndereco().getBairro());
        assertEquals(cliente1.getEndereco().getRua(), clienteDTO1.getEndereco().getRua());
        assertEquals(cliente1.getEndereco().getNumero(), clienteDTO1.getEndereco().getNumero());

        ClienteDTO clienteDTO2 = clienteDTOs.get(1);
        assertEquals(cliente2.getId(), clienteDTO2.getId());
        assertEquals(cliente2.getNome(), clienteDTO2.getNome());
        assertEquals(cliente2.getCpf(), clienteDTO2.getCpf());
        assertNotNull(clienteDTO2.getEndereco());
        assertEquals(cliente2.getEndereco().getBairro(), clienteDTO2.getEndereco().getBairro());
        assertEquals(cliente2.getEndereco().getRua(), clienteDTO2.getEndereco().getRua());
        assertEquals(cliente2.getEndereco().getNumero(), clienteDTO2.getEndereco().getNumero());
    }

    @Test
    public void testBuscarClientesPorNome() {
        // Dados de teste
        String nome = "Cliente 1";
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome(nome);
        cliente1.setCpf("123");
        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Bairro 1");
        endereco1.setRua("Rua 1");
        endereco1.setNumero(1);
        cliente1.setEndereco(endereco1);

        // Simulando o repositório
        when(clienteRepository.findByNome(nome)).thenReturn(List.of(cliente1));

        // Chamada ao serviço
        List<Cliente> clientesEncontrados = clienteService.buscarClientesPorNome(nome);

        // Verificações
        assertNotNull(clientesEncontrados);
        assertEquals(1, clientesEncontrados.size());
        Cliente clienteEncontrado = clientesEncontrados.get(0);
        assertEquals(cliente1.getId(), clienteEncontrado.getId());
        assertEquals(cliente1.getNome(), clienteEncontrado.getNome());
        assertEquals(cliente1.getCpf(), clienteEncontrado.getCpf());
        assertNotNull(clienteEncontrado.getEndereco());
        assertEquals(cliente1.getEndereco().getBairro(), clienteEncontrado.getEndereco().getBairro());
        assertEquals(cliente1.getEndereco().getRua(), clienteEncontrado.getEndereco().getRua());
        assertEquals(cliente1.getEndereco().getNumero(), clienteEncontrado.getEndereco().getNumero());
    }

    @Test
    public void testBuscarClientesPorCpf() {
        // Dados de teste
        String cpf = "123456";
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");
        cliente1.setCpf(cpf);
        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Bairro 1");
        endereco1.setRua("Rua 1");
        endereco1.setNumero(1);
        cliente1.setEndereco(endereco1);

        // Simulando o repositório
        when(clienteRepository.findByCpf(cpf)).thenReturn(List.of(cliente1));

        // Chamada ao serviço
        List<Cliente> clientesEncontrados = clienteService.buscarClientesPorCpf(cpf);

        // Verificações
        assertNotNull(clientesEncontrados);
        assertEquals(1, clientesEncontrados.size());
        Cliente clienteEncontrado = clientesEncontrados.get(0);
        assertEquals(cliente1.getId(), clienteEncontrado.getId());
        assertEquals(cliente1.getNome(), clienteEncontrado.getNome());
        assertEquals(cliente1.getCpf(), clienteEncontrado.getCpf());
        assertNotNull(clienteEncontrado.getEndereco());
        assertEquals(cliente1.getEndereco().getBairro(), clienteEncontrado.getEndereco().getBairro());
        assertEquals(cliente1.getEndereco().getRua(), clienteEncontrado.getEndereco().getRua());
        assertEquals(cliente1.getEndereco().getNumero(), clienteEncontrado.getEndereco().getNumero());
    }

    @Test
    public void testBuscarClientePorId() {
        // Dados de teste
        Long id = 1L;
        Cliente cliente1 = new Cliente();
        cliente1.setId(id);
        cliente1.setNome("Cliente 1");
        cliente1.setCpf("123");
        Endereco endereco1 = new Endereco();
        endereco1.setBairro("Bairro 1");
        endereco1.setRua("Rua 1");
        endereco1.setNumero(1);
        cliente1.setEndereco(endereco1);

        // Simulando o repositório
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente1));

        // Chamada ao serviço
        Optional<Cliente> clienteEncontrado = clienteService.buscarClientePorId(id);

        // Verifica se o cliente foi encontrado
        if (clienteEncontrado.isPresent()) {
            Cliente cliente = clienteEncontrado.get();

            // Realize as verificações dos atributos
            assertEquals(cliente1.getId(), cliente.getId());
            assertEquals(cliente1.getNome(), cliente.getNome());
            assertEquals(cliente1.getCpf(), cliente.getCpf());
            assertNotNull(cliente.getEndereco());
            assertEquals(cliente1.getEndereco().getBairro(), cliente.getEndereco().getBairro());
            assertEquals(cliente1.getEndereco().getRua(), cliente.getEndereco().getRua());
            assertEquals(cliente1.getEndereco().getNumero(), cliente.getEndereco().getNumero());
        } else {
            fail("Cliente não encontrado");
        }
    }

    @Test
    public void testAtualizarCliente() {
        // Dados de teste
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Novo Nome");
        clienteDTO.setCpf("789456123");

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(id);
        clienteAtualizado.setNome(clienteDTO.getNome());
        clienteAtualizado.setCpf(clienteDTO.getCpf());

        // Simulando o repositório
        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteAtualizado));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        // Chamada ao serviço
        Cliente clienteAtualizadoResultado = clienteService.atualizarCliente(id, clienteDTO);

        // Verificações
        assertNotNull(clienteAtualizadoResultado);
        assertEquals(clienteAtualizado.getId(), clienteAtualizadoResultado.getId());
        assertEquals(clienteAtualizado.getNome(), clienteAtualizadoResultado.getNome());
        assertEquals(clienteAtualizado.getCpf(), clienteAtualizadoResultado.getCpf());
    }

    @Test
    public void testAtualizarClienteNaoEncontrado() {
        // Dados de teste
        Long id = 1L;
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Novo Nome");
        clienteDTO.setCpf("789456123");

        // Simulando o repositório para um cliente não encontrado
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // Chamada ao serviço
        Cliente clienteAtualizadoResultado = clienteService.atualizarCliente(id, clienteDTO);

        // Verificações
        assertNull(clienteAtualizadoResultado);
    }

    @Test
    public void testDeletarCliente() {
        // Dados de teste
        Long id = 1L;

        // Simulando o repositório
        doNothing().when(clienteRepository).deleteById(id);

        // Chamada ao serviço
        clienteService.deletarCliente(id);

        // Verificações
        // Nenhuma exceção deve ser lançada se a exclusão for bem-sucedida
    }
}
