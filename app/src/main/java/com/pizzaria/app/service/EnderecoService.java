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

    public Endereco cadastrarEndereco(EnderecoDTO enderecoDTO) {
        // Mapear EnderecoDTO para Endereco
        Endereco novoEndereco = new Endereco();
        novoEndereco.setBairro(enderecoDTO.getBairro());
        novoEndereco.setRua(enderecoDTO.getRua());
        novoEndereco.setNumero(enderecoDTO.getNumero());

        return enderecoRepository.save(novoEndereco);
    }

    public Endereco atualizarEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco enderecoExistente = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        // Atualizar os campos do endereço existente com base nos dados do DTO
        enderecoExistente.setBairro(enderecoDTO.getBairro());
        enderecoExistente.setRua(enderecoDTO.getRua());
        enderecoExistente.setNumero(enderecoDTO.getNumero());

        return enderecoRepository.save(enderecoExistente);
    }


    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }
}
