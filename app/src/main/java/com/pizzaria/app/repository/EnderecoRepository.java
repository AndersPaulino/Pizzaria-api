package com.pizzaria.app.repository;

import com.pizzaria.app.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query("SELECT e FROM Endereco e WHERE e.bairro = :bairro")
    List<Endereco> buscarPorBairro(String bairro);

    @Query("SELECT e FROM Endereco e WHERE e.rua = :rua")
    List<Endereco> buscarPorRua(String rua);

    @Query("SELECT e FROM Endereco e WHERE e.numero = :numero")
    List<Endereco> buscarPorNumero(int numero);
}
