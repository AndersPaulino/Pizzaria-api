package com.pizzaria.app.service;

import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.EnderecoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@WebMvcTest(EnderecoService.class)
class EnderecoServiceTest {

    @MockBean
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCriarEndereco() {

        Endereco enderecoSalvo = new Endereco();
        enderecoSalvo.setId(1L);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoSalvo);

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.setBairro("Bairro Teste");
        enderecoDTO.setRua("Rua Teste");
        enderecoDTO.setNumero(123);

        EnderecoDTO enderecoCriadoDTO = enderecoService.criarEndereco(enderecoDTO);
        assertNotNull(enderecoCriadoDTO.getId());
        assertEquals(1L, enderecoCriadoDTO.getId());
    }

    @Test
    void testBuscarEnderecosPorBairro() {
        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findByBairro(anyString())).thenReturn(enderecosFicticios);
        List<Endereco> enderecosEncontrados = enderecoService.buscarEnderecosPorBairro("Bairro Teste");
        assertNotNull(enderecosEncontrados);
        assertTrue(enderecosEncontrados.isEmpty());
    }

    @Test
    void testBuscarEnderecosPorRua() {
        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findByRua(anyString())).thenReturn(enderecosFicticios);


        List<Endereco> enderecosEncontrados = enderecoService.buscarEnderecosPorRua("Rua Teste");


        assertNotNull(enderecosEncontrados);
        assertTrue(enderecosEncontrados.isEmpty());
    }

    @Test
    void testBuscarEnderecosPorNumero() {
        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findByNumero(anyInt())).thenReturn(enderecosFicticios);


        List<Endereco> enderecosEncontrados = enderecoService.buscarEnderecosPorNumero(123);


        assertNotNull(enderecosEncontrados);
        assertTrue(enderecosEncontrados.isEmpty());
    }

    @Test
    void testListarTodosEnderecos() {

        List<Endereco> enderecosFicticios = new ArrayList<>();
        when(enderecoRepository.findAll()).thenReturn(enderecosFicticios);


        List<Endereco> enderecosListados = enderecoService.listarTodosEnderecos();


        assertNotNull(enderecosListados);
        assertTrue(enderecosListados.isEmpty());
    }

    @Test
    void testBuscarEnderecoPorId() {
        Long enderecoId = 1L;
        Endereco enderecoFicticio = new Endereco();
        enderecoFicticio.setId(enderecoId);
        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.of(enderecoFicticio));

        Optional<Endereco> enderecoEncontrado = enderecoService.buscarEnderecoPorId(enderecoId);

        assertTrue(enderecoEncontrado.isPresent());
        assertEquals(enderecoId, enderecoEncontrado.get().getId());
    }

    @Test
    void testAtualizarEndereco() {

        Long enderecoId = 1L;
        Endereco enderecoFicticio = new Endereco();
        enderecoFicticio.setId(enderecoId);
        when(enderecoRepository.findById(enderecoId)).thenReturn(Optional.of(enderecoFicticio));


        Endereco enderecoAtualizado = new Endereco();
        enderecoAtualizado.setId(enderecoId);
        enderecoAtualizado.setBairro("Bairro Atualizado");
        enderecoAtualizado.setRua("Rua Atualizada");
        enderecoAtualizado.setNumero(456);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(enderecoAtualizado);


        EnderecoDTO enderecoDTOAtualizado = new EnderecoDTO();
        enderecoDTOAtualizado.setBairro("Bairro Atualizado");
        enderecoDTOAtualizado.setRua("Rua Atualizada");
        enderecoDTOAtualizado.setNumero(456);

        Endereco enderecoResultado = enderecoService.atualizarEndereco(enderecoId, enderecoDTOAtualizado);
        assertNotNull(enderecoResultado);
        assertEquals(enderecoId, enderecoResultado.getId());
        assertEquals("Bairro Atualizado", enderecoResultado.getBairro());
        assertEquals("Rua Atualizada", enderecoResultado.getRua());
        assertEquals(456, enderecoResultado.getNumero());
    }

    @Test
    void testDeletarEndereco() {
        Long enderecoId = 1L;
        doNothing().when(enderecoRepository).deleteById(enderecoId);
        enderecoService.deletarEndereco(enderecoId);
        verify(enderecoRepository, times(1)).deleteById(enderecoId);
    }
}
