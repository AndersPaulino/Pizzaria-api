package com.pizzaria.app.service;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.BebidaRepository;
import com.pizzaria.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }



}
