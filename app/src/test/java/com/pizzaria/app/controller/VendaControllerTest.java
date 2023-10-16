package com.pizzaria.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.entity.Venda;
import com.pizzaria.app.service.ProdutoService;
import com.pizzaria.app.service.VendaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VendaController.class)
public class VendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VendaService vendaService;

    @MockBean
    private ProdutoService produtoService;

    private static final String VENDA_API_URL = "/vendas";
    private static final String VENDA_API_URL_WITH_ID = "/vendas/";
    private static final String VENDA_BUSCAR_POR_EMITIR_NOTA_URL = "/vendas/por-emitir-nota?emitirNota=";
    private static final String VENDA_BUSCAR_POR_ENTREGAR_URL = "/vendas/por-entregar?entregar=";
    private static final String JSON_PATH_VALOR_VENDA = "$.valorVenda";
    private static final String JSON_PATH_ID = "$.id";
    @BeforeEach
    void setUp() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Cliente Test");

        Funcionario funcionario = new Funcionario();
        funcionario.setId(2L);
        funcionario.setNome("Funcionario Test");

        Produto produto = new Produto();
        produto.setId(3L);
        produto.setValorProduto(BigDecimal.valueOf(100.0));

        Venda venda = new Venda();
        venda.setId(1L);
        venda.setValorVenda(BigDecimal.valueOf(200.0));
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setProduto(produto);
        venda.setEmitirNota(true);
        venda.setEntregar(true);

        when(vendaService.buscarVendaPorId(1L)).thenReturn(Optional.of(venda));
        when(produtoService.buscarProdutoPorId(3L)).thenReturn(Optional.of(produto));
    }


    @Test
    void testCadastrarVenda() throws Exception {
        VendaDTO vendaDTO = new VendaDTO();

        vendaDTO.setEmitirNota(true);
        vendaDTO.setEntregar(true);
        vendaDTO.setValorVenda(BigDecimal.valueOf(200.0));

        VendaDTO vendaCadastrada = new VendaDTO();
        vendaCadastrada.setId(1L);
        vendaCadastrada.setEmitirNota(true);
        vendaCadastrada.setEntregar(true);
        vendaCadastrada.setValorVenda(BigDecimal.valueOf(200.0));

        when(vendaService.cadastrarVenda(any(VendaDTO.class))).thenReturn(vendaCadastrada);

        mockMvc.perform(MockMvcRequestBuilders.post(VENDA_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vendaDTO)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath(JSON_PATH_ID).value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath(JSON_PATH_VALOR_VENDA).value(200.0));
    }


    @Test
    void testBuscarVendasPorEmitirNota() throws Exception {
        boolean emitirNota = true;

        VendaDTO venda1 = new VendaDTO();
        venda1.setEmitirNota(true);
        venda1.setValorVenda(BigDecimal.valueOf(200.0));

        VendaDTO venda2 = new VendaDTO();
        venda2.setEmitirNota(true);
        venda2.setValorVenda(BigDecimal.valueOf(300.0));

        List<VendaDTO> vendas = new ArrayList<>();
        vendas.add(venda1);
        vendas.add(venda2);

        when(vendaService.buscarVendasPorEmitirNota(emitirNota)).thenReturn(vendas);

        mockMvc.perform(MockMvcRequestBuilders.get(VENDA_BUSCAR_POR_EMITIR_NOTA_URL + "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].emitirNota").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].valorVenda").value(200.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].emitirNota").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].valorVenda").value(300.0));
    }


    @Test
    void testBuscarVendasPorEntregar() throws Exception {
        boolean entregar = true;

        VendaDTO venda1 = new VendaDTO();
        venda1.setEntregar(true);
        venda1.setValorVenda(BigDecimal.valueOf(50.0));

        VendaDTO venda2 = new VendaDTO();
        venda2.setEntregar(true);
        venda2.setValorVenda(BigDecimal.valueOf(75.0));

        List<VendaDTO> vendas = new ArrayList<>();
        vendas.add(venda1);
        vendas.add(venda2);

        when(vendaService.buscarVendasPorEntregar(entregar)).thenReturn(vendas);

        mockMvc.perform(MockMvcRequestBuilders.get(VENDA_BUSCAR_POR_ENTREGAR_URL + "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].entregar").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].valorVenda").value(50.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].entregar").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].valorVenda").value(75.0));
    }

    @Test
    void testBuscarVendaPorId() throws Exception {
        Long vendaId = 1L;

        Venda venda = new Venda();
        venda.setId(vendaId);
        venda.setValorVenda(BigDecimal.valueOf(200.0));

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        venda.setCliente(cliente);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(2L);
        venda.setFuncionario(funcionario);

        Produto produto = new Produto();
        produto.setId(3L);
        venda.setProduto(produto);

        Optional<Venda> optionalVenda = Optional.of(venda);

        when(vendaService.buscarVendaPorId(vendaId)).thenReturn(optionalVenda);

        mockMvc.perform(MockMvcRequestBuilders.get(VENDA_API_URL_WITH_ID + vendaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(JSON_PATH_ID).value(vendaId))
                .andExpect(MockMvcResultMatchers.jsonPath(JSON_PATH_VALOR_VENDA).value(200.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.funcionario.id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.produto.id").value(3L));
    }



    @Test
    void testDeletarVenda() throws Exception {
        long vendaId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete(VENDA_API_URL_WITH_ID + vendaId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
