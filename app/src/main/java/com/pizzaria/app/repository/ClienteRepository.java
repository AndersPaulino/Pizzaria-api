package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Adicione métodos de consulta personalizados, se necessário
}
