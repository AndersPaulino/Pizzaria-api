package com.pizzaria.app.service;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());

        // Set Endereco directly from the ClienteDTO
        Endereco endereco = new Endereco();
        endereco.setBairro(clienteDTO.getEndereco().getBairro());
        endereco.setRua(clienteDTO.getEndereco().getRua());
        endereco.setNumero(clienteDTO.getEndereco().getNumero());

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }
}
