package com.pizzaria.app.service;

import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.entity.Cliente;
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

    private final ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> findAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream().map(ClienteDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ClienteDTO findById(Long id) {
        Cliente entity = clienteRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return new ClienteDTO(entity);
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
    public Cliente atualizar(Long id, Cliente clienteAtualizado){
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("O Cliente com o ID " + id + " não existe."));
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());

        return clienteRepository.save(clienteExistente);
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
