package com.pizzaria.app.service;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.ClienteRepository;
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

@WebMvcTest(ClienteService.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
