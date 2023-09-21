package com.pizzaria.app.testService;

import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.EnderecoRepository;
import com.pizzaria.app.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCriarEndereco() {
        // Configurar comportamento do mock para salvar o endereço
        Endereco enderecoSalvo = new Endereco();
        enderecoSalvo.setId(1L);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoSalvo);

        // Criar um DTO de Endereço fictício
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setBairro("Bairro Teste");
        enderecoDTO.setRua("Rua Teste");
        enderecoDTO.setNumero(123);

        // Chamar o método de serviço para criar o endereço
        EnderecoDTO enderecoCriadoDTO = enderecoService.criarEndereco(enderecoDTO);

        // Verificar se o ID foi atribuído ao DTO criado
        assertNotNull(enderecoCriadoDTO.getId());
        assertEquals(1L, enderecoCriadoDTO.getId());
    }

    @Test
    public void testBuscarEnderecosPorBairro() {
        // Configurar comportamento do mock para retornar uma lista de endereços fictícia
        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findByBairro(anyString())).thenReturn(enderecosFicticios);

        // Chamar o método de serviço para buscar endereços por bairro
        List<Endereco> enderecosEncontrados = enderecoService.buscarEnderecosPorBairro("Bairro Teste");

        // Verificar se a lista retornada não está nula
        assertNotNull(enderecosEncontrados);
        assertTrue(enderecosEncontrados.isEmpty()); // Deve estar vazia para este exemplo
    }

    @Test
    public void testBuscarEnderecosPorRua() {
        // Configurar comportamento do mock para retornar uma lista de endereços fictícia
        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findByRua(anyString())).thenReturn(enderecosFicticios);

        // Chamar o método de serviço para buscar endereços por rua
        List<Endereco> enderecosEncontrados = enderecoService.buscarEnderecosPorRua("Rua Teste");

        // Verificar se a lista retornada não está nula
        assertNotNull(enderecosEncontrados);
        assertTrue(enderecosEncontrados.isEmpty()); // Deve estar vazia para este exemplo
    }

    @Test
    public void testBuscarEnderecosPorNumero() {
        // Configurar comportamento do mock para retornar uma lista de endereços fictícia
        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findByNumero(anyInt())).thenReturn(enderecosFicticios);

        // Chamar o método de serviço para buscar endereços por número
        List<Endereco> enderecosEncontrados = enderecoService.buscarEnderecosPorNumero(123);

        // Verificar se a lista retornada não está nula
        assertNotNull(enderecosEncontrados);
        assertTrue(enderecosEncontrados.isEmpty()); // Deve estar vazia para este exemplo
    }

    @Test
    public void testListarTodosEnderecos() {
        // Configurar comportamento do mock para retornar uma lista de endereços fictícia
        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findAll()).thenReturn(enderecosFicticios);

        // Chamar o método de serviço para listar todos os endereços
        List<Endereco> enderecosListados = enderecoService.listarTodosEnderecos();

        // Verificar se a lista retornada não está nula
        assertNotNull(enderecosListados);
        assertTrue(enderecosListados.isEmpty()); // Deve estar vazia para este exemplo
    }

    @Test
    public void testBuscarEnderecoPorId() {
        // Configurar comportamento do mock para retornar um endereço fictício
        Long enderecoId = 1L;
        Endereco enderecoFicticio = new Endereco();
        enderecoFicticio.setId(enderecoId);
        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.of(enderecoFicticio));

        // Chamar o método de serviço para buscar um endereço por ID
        Optional<Endereco> enderecoEncontrado = enderecoService.buscarEnderecoPorId(enderecoId);

        // Verificar se o resultado não está vazio e se corresponde ao ID esperado
        assertTrue(enderecoEncontrado.isPresent());
        assertEquals(enderecoId, enderecoEncontrado.get().getId());
    }

    @Test
    public void testAtualizarEndereco() {
        // Configurar comportamento do mock para retornar um endereço fictício
        Long enderecoId = 1L;
        Endereco enderecoFicticio = new Endereco();
        enderecoFicticio.setId(enderecoId);
        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.of(enderecoFicticio));

        // Configurar comportamento do mock para retornar o endereço atualizado
        Endereco enderecoAtualizado = new Endereco();
        enderecoAtualizado.setId(enderecoId);
        enderecoAtualizado.setBairro("Bairro Atualizado");
        enderecoAtualizado.setRua("Rua Atualizada");
        enderecoAtualizado.setNumero(456);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoAtualizado);

        // Criar um DTO de Endereço fictício com informações atualizadas
        EnderecoDTO enderecoDTOAtualizado = new EnderecoDTO();
        enderecoDTOAtualizado.setBairro("Bairro Atualizado");
        enderecoDTOAtualizado.setRua("Rua Atualizada");
        enderecoDTOAtualizado.setNumero(456);

        // Chamar o método de serviço para atualizar o endereço
        Endereco enderecoResultado = enderecoService.atualizarEndereco(enderecoId, enderecoDTOAtualizado);

        // Verificar se o endereço retornado corresponde ao esperado
        assertNotNull(enderecoResultado);
        assertEquals(enderecoId, enderecoResultado.getId());
        assertEquals("Bairro Atualizado", enderecoResultado.getBairro());
        assertEquals("Rua Atualizada", enderecoResultado.getRua());
        assertEquals(456, enderecoResultado.getNumero());
    }

    @Test
    public void testDeletarEndereco() {
        // Configurar comportamento do mock para não fazer nada quando deletar um endereço
        Long enderecoId = 1L;
        doNothing().when(enderecoRepository).deleteById(enderecoId);

        // Chamar o método de serviço para deletar o endereço
        enderecoService.deletarEndereco(enderecoId);

        // Verificar se o método delete do repositório foi chamado com o ID correto
        verify(enderecoRepository, times(1)).deleteById(enderecoId);
    }
}
