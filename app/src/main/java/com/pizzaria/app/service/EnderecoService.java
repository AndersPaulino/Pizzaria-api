package com.pizzaria.app.service;

import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {


    private final EnderecoRepository enderecoRepository;
    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public List<Endereco> buscarEnderecosPorBairro(String bairro) {
        return enderecoRepository.findByBairro(bairro);
    }
    @Transactional(readOnly = true)
    public List<Endereco> buscarEnderecosPorRua(String rua) {
        return enderecoRepository.findByRua(rua);
    }
    @Transactional(readOnly = true)
    public List<Endereco> buscarEnderecosPorNumero(int numero) {
        return enderecoRepository.findByNumero(numero);
    }

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecos.stream().map(EnderecoDTO::new).toList();
    }
    @Transactional(readOnly = true)
    public Optional<EnderecoDTO> findById(Long id) {
        return enderecoRepository.findById(id).map(EnderecoDTO::new);
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Endereco endereco){
        enderecoRepository.save(endereco);
    }
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void atualizar(Long id, Endereco endereco){
        Optional<Endereco> enderecos = enderecoRepository.findById(id);
        if (enderecos.isPresent()){
            Endereco enderecoExistente = enderecos.get();
            if (endereco.getBairro() != null) {
                enderecoExistente.setBairro(endereco.getBairro());
                enderecoRepository.save(enderecoExistente);
            }
            if (endereco.getRua() != null) {
                enderecoExistente.setRua(endereco.getRua());
                enderecoRepository.save(enderecoExistente);
            }
            if (endereco.getNumero() != 0) {
                enderecoExistente.setNumero(endereco.getNumero());
                enderecoRepository.save(enderecoExistente);
            }
        }else {
            throw new IllegalArgumentException("Id do Endereço não encontrado!");
        }
    }
    public void deleteEndereco(Long id){
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Endereço não encontrador com ID: " + id));
        enderecoRepository.delete(endereco);
    }
    public void desativar(Long id){
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
        Endereco endereco = enderecoOptional.get();

        if (enderecoOptional.isPresent()){
            endereco.setAtivo(false);
            enderecoRepository.save(endereco);
            throw new IllegalArgumentException("Endereço desativado com sucesso!");
        } else {
            throw new IllegalArgumentException("ID do endereço inválido!");
        }
    }
}
