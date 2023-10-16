package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @EntityGraph(attributePaths = "endereco")
    List<Cliente> findByNome(String nome);

    @EntityGraph(attributePaths = "endereco")
    List<Cliente> findByCpf(String cpf);
}
