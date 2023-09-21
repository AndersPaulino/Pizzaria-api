package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Bebida;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    @EntityGraph(attributePaths = "nomeBebida")
    List<Bebida> findByNomeBebida(String nomeBebida);
}
