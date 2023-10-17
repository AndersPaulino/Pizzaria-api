package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Bebida;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BebibaDTOTest {
    private Bebida bebida;
    @Test
    void testConstructorWithParameters() {
        Long id = 1L;
        String nomeBebida = "Coca Cola";
        BigDecimal valorBebida = new BigDecimal("2.50");
        boolean ativo = true;
        LocalDateTime registro = LocalDateTime.now();
        LocalDateTime atualizar = LocalDateTime.now();

        BebidaDTO bebidaDTO = new BebidaDTO(id, nomeBebida, valorBebida, ativo, registro, atualizar);

        assertThat(bebidaDTO.getId()).isEqualTo(id);
        assertThat(bebidaDTO.getNomeBebida()).isEqualTo(nomeBebida);
        assertThat(bebidaDTO.getValorBebida()).isEqualTo(valorBebida);
        assertThat(bebidaDTO.isAtivo()).isEqualTo(ativo);
        assertThat(bebidaDTO.getRegistro()).isEqualTo(registro);
        assertThat(bebidaDTO.getAtualizar()).isEqualTo(atualizar);
    }

    @Test
    void testConstructorWithBebidaObject() {
        bebida = new Bebida();
        bebida.setId(1L);
        bebida.setNomeBebida("Pepsi");
        bebida.setValorBebida(new BigDecimal("2.00"));
        bebida.setAtivo(true);
        bebida.setRegistro(LocalDateTime.now());
        bebida.setAtualizar(LocalDateTime.now());

        BebidaDTO bebidaDTO = new BebidaDTO(bebida);

        assertThat(bebidaDTO.getId()).isEqualTo(bebida.getId());
        assertThat(bebidaDTO.getNomeBebida()).isEqualTo(bebida.getNomeBebida());
        assertThat(bebidaDTO.getValorBebida()).isEqualTo(bebida.getValorBebida());
        assertThat(bebidaDTO.isAtivo()).isEqualTo(bebida.isAtivo());
        assertThat(bebidaDTO.getRegistro()).isEqualTo(bebida.getRegistro());
        assertThat(bebidaDTO.getAtualizar()).isEqualTo(bebida.getAtualizar());
    }

    @Test
    void testGetterAndSetterMethods() {
        bebida = new Bebida();
        BebidaDTO bebidaDTO = new BebidaDTO(bebida);

        bebidaDTO.setId(1L);
        bebidaDTO.setNomeBebida("Sprite");
        bebidaDTO.setValorBebida(new BigDecimal("1.75"));
        bebidaDTO.setAtivo(true);
        bebidaDTO.setRegistro(LocalDateTime.now());
        bebidaDTO.setAtualizar(LocalDateTime.now().plusHours(1));

        assertThat(bebidaDTO.getId()).isEqualTo(1L);
        assertThat(bebidaDTO.getNomeBebida()).isEqualTo("Sprite");
        assertThat(bebidaDTO.getValorBebida()).isEqualTo(new BigDecimal("1.75"));
        assertThat(bebidaDTO.isAtivo()).isEqualTo(true);
        assertThat(bebidaDTO.getRegistro()).isBeforeOrEqualTo(LocalDateTime.now());
        assertThat(bebidaDTO.getAtualizar()).isAfter(LocalDateTime.now());
    }
}
