package com.pizzaria.app.service;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());

        if (cliente.getEndereco() != null) {
            EnderecoDTO enderecoDTO = new EnderecoDTO();
            enderecoDTO.setId(cliente.getEndereco().getId());
            enderecoDTO.setBairro(cliente.getEndereco().getBairro());
            enderecoDTO.setRua(cliente.getEndereco().getRua());
            enderecoDTO.setNumero(cliente.getEndereco().getNumero());

            clienteDTO.setEndereco(enderecoDTO);
        }

        return clienteDTO;
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());

        Endereco endereco = new Endereco();
        endereco.setBairro(clienteDTO.getEndereco().getBairro());
        endereco.setRua(clienteDTO.getEndereco().getRua());
        endereco.setNumero(clienteDTO.getEndereco().getNumero());

        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);

        clienteDTO.setId(cliente.getId());
        return clienteDTO;
    }


    public List<Cliente> buscarClientesPorNome(String nome) {
        return clienteRepository.findByNome(nome);
    }

    public List<Cliente> buscarClientesPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<ClienteDTO> listarTodosClientesDTO() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente atualizarCliente(Long id, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            cliente.setNome(clienteDTO.getNome());
            cliente.setCpf(clienteDTO.getCpf());

            return clienteRepository.save(cliente);
        }

        return null; // Ou lançar uma exceção adequada
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
