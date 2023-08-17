package com.pizzaria.app.service;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private ClienteRepository clienteRepository;
    private EnderecoService enderecoService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, EnderecoService enderecoService) {
        this.clienteRepository = clienteRepository;
        this.enderecoService = enderecoService;
    }

    public ClienteDTO findById(Long id) {
        Optional<Cliente> entity = clienteRepository.findById(id);
        if (entity.isPresent()) {
            return new ClienteDTO(entity.get());
        } else {
            return null;
        }
    }

    public List<ClienteDTO> listarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = new ClienteDTO(cliente);
            clientesDTO.add(clienteDTO);
        }

        return clientesDTO;
    }

    public List<ClienteDTO> buscarPorNome(String nome) {
        List<Cliente> clientes = clienteRepository.buscarPorNome(nome);
        List<ClienteDTO> dtos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            dtos.add(new ClienteDTO(cliente));
        }
        return dtos;
    }

    public ClienteDTO buscarPorCpf(String cpf) {
        Optional<Cliente> entity = clienteRepository.buscarPorCpf(cpf);
        if (entity.isPresent()) {
            return new ClienteDTO(entity.get());
        } else {
            return null;
        }
    }

    public List<ClienteDTO> buscarPorTelefone(String telefone) {
        List<Cliente> clientes = clienteRepository.buscarPorTelefone(telefone);
        List<ClienteDTO> dtos = new ArrayList<>();
        for (Cliente cliente : clientes) {
            dtos.add(new ClienteDTO(cliente));
        }
        return dtos;
    }




    public Cliente cadastrarCliente(ClienteDTO clienteDTO) {
        // Cadastrar o endereço primeiro e obter a instância do endereço salvo
        Endereco enderecoSalvo = enderecoService.cadastrarEndereco((Endereco) clienteDTO.getEndereco());

        // Mapear ClienteDTO para Cliente
        Cliente novoCliente = new Cliente();
        novoCliente.setNome(clienteDTO.getNome());
        novoCliente.setTelefone(clienteDTO.getTelefone());
        novoCliente.setCpf(clienteDTO.getCpf());

        // Associar o endereço salvo ao cliente
        novoCliente.getEnderecos().add(enderecoSalvo);

        return clienteRepository.save(novoCliente);
    }

    public Cliente atualizarCliente(Long clienteId, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualizar os campos do cliente existente com base nos dados do DTO
        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setTelefone(clienteDTO.getTelefone());
        clienteExistente.setCpf(clienteDTO.getCpf());

        // Se o clienteDTO tiver um endereço, atualize os campos do endereço associado
        if (clienteDTO.getEndereco() != null) {
            Endereco enderecoExistente = clienteExistente.getEnderecos().get(0); // Supondo um único endereço
            enderecoExistente.setBairro(clienteDTO.getEndereco().get(0).getBairro());
            enderecoExistente.setRua(clienteDTO.getEndereco().get(0).getRua());
            enderecoExistente.setNumero(clienteDTO.getEndereco().get(0).getNumero());
        }

        return clienteRepository.save(clienteExistente);
    }


    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
