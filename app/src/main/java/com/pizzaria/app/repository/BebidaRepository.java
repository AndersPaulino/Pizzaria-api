package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {
    @Query("SELECT e FROM Bebida e WHERE e.nomeBebida = :nomeBebida")
    public List<Bebida> findByName(@Param("nomeBebida") String nomeBebida);
}
