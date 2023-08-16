package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
    List<Cliente> buscarPorNome(String nome);

    @Query("SELECT c FROM Cliente c WHERE c.cpf = :cpf")
    Optional<Cliente> buscarPorCpf(String cpf);

    @Query("SELECT c FROM Cliente c WHERE c.telefone = :telefone")
    List<Cliente> buscarPorTelefone(String telefone);
}
