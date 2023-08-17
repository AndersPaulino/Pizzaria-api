package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    List<Venda> findByEmitirNota(boolean emitirNota);

    List<Venda> findByEntregar(boolean entregar);
}
