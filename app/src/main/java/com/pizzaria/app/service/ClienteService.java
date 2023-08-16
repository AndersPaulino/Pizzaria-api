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



    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
        // Converte ClienteDTO em um objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setTelefone(clienteDTO.getTelefone());
        cliente.setCpf(clienteDTO.getCpf());

        EnderecoDTO enderecoDTO = clienteDTO.getEndereco();
        Endereco endereco = new Endereco(enderecoDTO.getBairro(), enderecoDTO.getRua(), enderecoDTO.getNumero());
        cliente.setEnderecos(Collections.singletonList(endereco));

        // Salva o cliente no banco de dados
        Cliente clienteCadastrado = clienteRepository.save(cliente);

        // Cria um novo ClienteDTO a partir do cliente cadastrado
        ClienteDTO clienteCadastradoDTO = new ClienteDTO(clienteCadastrado);

        return clienteCadastradoDTO;
    }

    public ClienteDTO atualizarCliente(Long clienteId, ClienteDTO clienteDTO) {
        Cliente clienteExistente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualiza as informações do cliente existente com base no DTO
        clienteExistente.setNome(clienteDTO.getNome());
        clienteExistente.setTelefone(clienteDTO.getTelefone());
        clienteExistente.setCpf(clienteDTO.getCpf());

        EnderecoDTO enderecoDTO = clienteDTO.getEndereco();
        Endereco enderecoAtualizado = new Endereco(enderecoDTO.getBairro(), enderecoDTO.getRua(), enderecoDTO.getNumero());

        // Como você tem uma lista de endereços no DTO, você precisará atualizar a lista de endereços do cliente
        List<Endereco> enderecosAtualizados = clienteExistente.getEnderecos().stream()
                .map(endereco -> endereco.getId().equals(enderecoDTO.getId()) ? enderecoAtualizado : endereco)
                .collect(Collectors.toList());

        clienteExistente.setEnderecos(enderecosAtualizados);

        // Salva as alterações no cliente no banco de dados
        Cliente clienteAtualizado = clienteRepository.save(clienteExistente);

        // Cria um novo ClienteDTO a partir do cliente atualizado
        ClienteDTO clienteAtualizadoDTO = new ClienteDTO(clienteAtualizado);

        return clienteAtualizadoDTO;
    }


    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
