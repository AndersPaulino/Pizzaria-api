package com.pizzaria.app.service;

import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public List<Cliente> buscarClientesPorNome(String nome) {
        return clienteRepository.buscarPorNome(nome);
    }

    public List<Cliente> buscarClientesPorEndereco(String endereco) {
        return clienteRepository.buscarPorEndereco(endereco);
    }

}
