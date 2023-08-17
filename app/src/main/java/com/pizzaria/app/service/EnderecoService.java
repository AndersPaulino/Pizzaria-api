package com.pizzaria.app.service;

import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.entity.Endereco;
import com.pizzaria.app.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO criarEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco();
        endereco.setBairro(enderecoDTO.getBairro());
        endereco.setRua(enderecoDTO.getRua());
        endereco.setNumero(enderecoDTO.getNumero());

        enderecoRepository.save(endereco);

        enderecoDTO.setId(endereco.getId());
        return enderecoDTO;
    }

    public List<Endereco> buscarEnderecosPorBairro(String bairro) {
        return enderecoRepository.findByBairro(bairro);
    }

    public List<Endereco> buscarEnderecosPorRua(String rua) {
        return enderecoRepository.findByRua(rua);
    }

    public List<Endereco> buscarEnderecosPorNumero(int numero) {
        return enderecoRepository.findByNumero(numero);
    }

    public List<Endereco> listarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> buscarEnderecoPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);

        if (enderecoOptional.isPresent()) {
            Endereco endereco = enderecoOptional.get();
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setRua(enderecoDTO.getRua());
            endereco.setNumero(enderecoDTO.getNumero());

            return enderecoRepository.save(endereco);
        }

        return null; // Ou lançar uma exceção adequada
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }
}
