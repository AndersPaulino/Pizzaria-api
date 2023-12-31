package com.pizzaria.app.service;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.dto.FuncionarioDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class ClienteService {

    private ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO(cliente);
        clienteDTO.setId(cliente.getId());
        clienteDTO.setNome(cliente.getNome());
        clienteDTO.setCpf(cliente.getCpf());
        return clienteDTO;
    }

    @Transactional(readOnly = true)
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<ClienteDTO> buscarClientePorIdDTO(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(ClienteDTO::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public ClienteDTO findByNome(String nome){
        Cliente cliente = clienteRepository.findByNome(nome);
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public ClienteDTO findByCpf(String cpf){
        Cliente cliente = clienteRepository.findByCpf(cpf);
        return new ClienteDTO(cliente);
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByAtivo(boolean ativo){
        List<Cliente> clientes = clienteRepository.findByAtivo(ativo);

        return clientes.stream()
                .map(ClienteDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByDiaRegistro(LocalDate registro){
        List<Cliente> clientes = clienteRepository.findByDiaRegistro(registro);

        return clientes.stream()
                .map(ClienteDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findByDiaAtualizar(LocalDate atualizar){
        List<Cliente> clientes = clienteRepository.findByDiaAtualizar(atualizar);

        return clientes.stream()
                .map(ClienteDTO::new)
                .toList();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Cliente cliente){
        clienteRepository.save(cliente);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Cliente cliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        Cliente cliente1 = clienteOptional.get();
        if (clienteOptional.isPresent()){
            clienteRepository.save(cliente1);
        }else {
            throw new IllegalArgumentException("Id do Cliente não encontrado!");
        }
    }

    public void deleteClient(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Client não encontrado com ID: " + id));
        clienteRepository.delete(cliente);
    }

    public void desativar(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        Cliente cliente = clienteOptional.get();

        if (clienteOptional.isPresent()){
            cliente.setAtivo(false);
            clienteRepository.save(cliente);
            throw new IllegalArgumentException("Cliente desativado com sucesso!");
        }else {
            throw new IllegalArgumentException("ID do Cliente inválido!");
        }
    }
}
