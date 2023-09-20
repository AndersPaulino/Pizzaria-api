package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.entity.Cliente;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    @EntityGraph(attributePaths = "nomeBebida")
    List<Bebida> buscarBebidaporNome(String nomeBebida);
}
