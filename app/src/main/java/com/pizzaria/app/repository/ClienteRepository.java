package com.pizzaria.app.repository;


import com.pizzaria.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.EntityGraph;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @EntityGraph(attributePaths = "endereco")
    Cliente findByNome(String nome);

    @EntityGraph(attributePaths = "endereco")
    Cliente findByCpf(String cpf);

    @Query("SELECT e FROM Cliente e WHERE e.ativo = :ativo")
    public List<Cliente> findByAtivo(@Param("ativo") boolean ativo);

    @Query("SELECT e FROM Client e WHERE DATE(e.registro) = :registro")
    List<Cliente> findByDiaRegistro(@Param("registro") LocalDate registro);

    @Query("SELECT e FROM Cliente e WHERE DATE(e.atualizar) = :atualizar")
    List<Cliente> findByDiaAtualizar(@Param("atualizar") LocalDate registro);
}
