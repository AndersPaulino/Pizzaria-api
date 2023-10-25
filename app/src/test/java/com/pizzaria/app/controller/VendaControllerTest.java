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
class VendaControllerTest {

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
    }


}
