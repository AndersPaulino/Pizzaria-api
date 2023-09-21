package com.pizzaria.app.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.app.controller.ProdutoController;
import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.ProdutoRepository;
import com.pizzaria.app.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService produtoService;

    @MockBean
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() throws Exception {
        Long produtoId = 1L;
        Produto produto = new Produto();
        produto.setId(produtoId);
        produto.setValorProduto(BigDecimal.valueOf(10.0));

        when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produto));

        mockMvc.perform(MockMvcRequestBuilders.get("/produto/{id}", produtoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(produtoId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorProduto").value(10.0));
    }

    @Test
    public void testFindAll() throws Exception {
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

        mockMvc.perform(MockMvcRequestBuilders.get("/produto")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].valorProduto").value(10.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].valorProduto").value(15.0));
    }


    @Test
    public void testDeletarProduto() throws Exception {
        Long produtoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/produto/{id}", produtoId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
