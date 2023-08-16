package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    Optional<Cliente> findById(Long id);
    @Query("SELECT c FROM Cliente c WHERE c.nome = :nome")
    List<Cliente> buscarPorNome(String nome);

    @Query("SELECT c FROM Cliente c WHERE c.endereco = :endereco")
    List<Cliente> buscarPorEndereco(String endereco);

}
