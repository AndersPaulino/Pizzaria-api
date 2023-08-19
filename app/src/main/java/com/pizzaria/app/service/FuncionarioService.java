package com.pizzaria.app.service;

import com.pizzaria.app.dto.FuncionarioDTO;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    private FuncionarioDTO convertToDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        return funcionarioDTO;
    }

    public List<FuncionarioDTO> listarTodosFuncionariosDTO() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<FuncionarioDTO> buscarFuncionarioPorIdDTO(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(this::convertToDTO);
    }

    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public List<FuncionarioDTO> buscarFuncionariosPorNomeDTO(String nome) {
        List<Funcionario> funcionarios = funcionarioRepository.buscarPorNome(nome);
        return funcionarios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());

        funcionarioRepository.save(funcionario);

        funcionarioDTO.setId(funcionario.getId());
        return funcionarioDTO;
    }

    public FuncionarioDTO atualizarFuncionario(Long id, FuncionarioDTO funcionarioDTO) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            Funcionario funcionario = funcionarioOptional.get();
            funcionario.setNome(funcionarioDTO.getNome());

            funcionarioRepository.save(funcionario);
            return funcionarioDTO;
        }

        return null;
    }

    public void deletarFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

}
