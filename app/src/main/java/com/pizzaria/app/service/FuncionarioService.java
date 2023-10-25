package com.pizzaria.app.service;

import com.pizzaria.app.dto.FuncionarioDTO;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository){this.funcionarioRepository = funcionarioRepository;}

    private FuncionarioDTO convertToDTO(Funcionario funcionario) {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario);
        funcionarioDTO.setId(funcionario.getId());
        funcionarioDTO.setNome(funcionario.getNome());
        return funcionarioDTO;
    }

    @Transactional(readOnly = true)
    public List<FuncionarioDTO> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(FuncionarioDTO::new).toList();
    }
    public List<FuncionarioDTO> listarTodosFuncionariosDTO() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<FuncionarioDTO> buscarFuncionarioPorIdDTO(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.map(this::convertToDTO);
    }

    @Transactional(readOnly = true)
    public Optional<FuncionarioDTO> findById(Long id) {
        return funcionarioRepository.findById(id).map(FuncionarioDTO::new);
    }

    public List<FuncionarioDTO> buscarFuncionariosPorNomeDTO(String nome) {
        List<Funcionario> funcionarios = funcionarioRepository.buscarPorNome(nome);
        return funcionarios.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Funcionario atualizarFuncionario(Long id, Funcionario funcionario) {
        validarFuncionario(funcionario);
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findById(id);
        if (funcionarioOptional.isPresent()) {
            Funcionario funcionarioExistente = funcionarioOptional.get();

            if (funcionario.getNome() != null) {
                funcionarioExistente.setNome(funcionario.getNome());
            }
            return funcionarioRepository.save(funcionarioExistente);
        } else {
            throw new IllegalArgumentException("Id do Funcionário inválido!");
        }
    }

    private void validarFuncionario(Funcionario funcionario) {
        if (funcionario.getNome() == null) {
            throw new IllegalArgumentException("Nome do Funcionário não informado!");
        }
    }


    public void deletarFuncionario(Long funcionarioId) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrada com ID: " + funcionarioId));
        funcionarioRepository.delete(funcionarioExistente);
    }

}
