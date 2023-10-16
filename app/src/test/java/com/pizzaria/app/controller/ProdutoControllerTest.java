package com.pizzaria.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.ProdutoRepository;
import com.pizzaria.app.service.ProdutoService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    private static final String PRODUTO_API_URL = "/produto";
    private static final String PRODUTO_API_URL_WITH_ID = "/produto/";
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

    }

    @Test
    void testFindById() throws Exception {
        Long produtoId = 1L;
        Produto produto = new Produto();
        produto.setId(produtoId);
        produto.setValorProduto(BigDecimal.valueOf(10.0));

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));

        mockMvc.perform(MockMvcRequestBuilders.get(PRODUTO_API_URL_WITH_ID+produtoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(produtoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorProduto").value(10.0));
    }

    @Test
    void testFindAll() throws Exception {
        Produto produto1 = new Produto();
        produto1.setId(1L);
        produto1.setValorProduto(BigDecimal.valueOf(10.0));

        Produto produto2 = new Produto();
        produto2.setId(2L);
        produto2.setValorProduto(BigDecimal.valueOf(15.0));

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto1);
        produtos.add(produto2);

        when(produtoRepository.findAll()).thenReturn(produtos);

        mockMvc.perform(MockMvcRequestBuilders.get(PRODUTO_API_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].valorProduto").value(10.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].valorProduto").value(15.0));
    }
    @Test
    void testCadastrarProduto() throws Exception {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setValorProduto(BigDecimal.valueOf(20.0));

        when(produtoService.cadastrarProduto(produtoDTO)).thenReturn(produtoDTO);

        mockMvc.perform(MockMvcRequestBuilders.post(PRODUTO_API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isOk());
    }


    @Test
    void testAtualizarProduto() throws Exception {
        Long produtoId = 1L;
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setValorProduto(BigDecimal.valueOf(25.0));

        when(produtoService.atualizarProduto(produtoId, produtoDTO)).thenReturn(produtoDTO);

        mockMvc.perform(MockMvcRequestBuilders.put(PRODUTO_API_URL_WITH_ID+produtoId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(produtoDTO)))
                .andExpect(status().isOk());
    }
    @Test
    void testDeletarProduto() throws Exception {
        Long produtoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete(PRODUTO_API_URL_WITH_ID+produtoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
