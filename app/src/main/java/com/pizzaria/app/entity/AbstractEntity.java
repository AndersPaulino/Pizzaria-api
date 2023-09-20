package com.pizzaria.app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, unique = true)
    private Long id;
    @Getter @Setter
    @Column(name = "ativo", nullable = false)
    private boolean ativo;
    @Getter @Setter
    @Column(name = "registro", nullable = false)
    private LocalDateTime registro;
    @Getter @Setter
    @Column(name = "atualizar")
    private LocalDateTime atualizar;

    @PrePersist
    private void prePersist(){
        this.registro = LocalDateTime.now();
        this.ativo = true;
    }
    @PreUpdate
    private void preUpdate(){
        this.atualizar = LocalDateTime.now();
    }
}
