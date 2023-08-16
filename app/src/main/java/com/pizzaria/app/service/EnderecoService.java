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

    private EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id);
    }

    public List<Endereco> listarTodosEnderecos() {
        return enderecoRepository.findAll();
    }

    public List<Endereco> buscarPorBairro(String bairro) {
        return enderecoRepository.buscarPorBairro(bairro);
    }

    public List<Endereco> buscarPorRua(String rua) {
        return enderecoRepository.buscarPorRua(rua);
    }

    public List<Endereco> buscarPorNumero(int numero) {
        return enderecoRepository.buscarPorNumero(numero);
    }

    public Endereco cadastrarEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }


    public Endereco atualizarEndereco(Long id, Endereco enderecoAtualizado) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);
        if (enderecoExistente.isPresent()) {
            Endereco endereco = enderecoExistente.get();
            endereco.setBairro(enderecoAtualizado.getBairro());
            endereco.setRua(enderecoAtualizado.getRua());
            endereco.setNumero(enderecoAtualizado.getNumero());
            return enderecoRepository.save(endereco);
        } else {
            return null;
        }
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }
}
