package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByBairro(String bairro);
    List<Endereco> findByRua(String rua);
    List<Endereco> findByNumero(int numero);
}
