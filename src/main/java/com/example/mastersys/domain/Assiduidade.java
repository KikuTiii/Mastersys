package com.example.mastersys.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assiduidade")
@Getter
@Setter
public class Assiduidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matricula_id", nullable = false)
    private Matricula matricula;

    @Column(name = "data_entrada", nullable = false)
    private LocalDateTime dataEntrada = LocalDateTime.now();

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @PrePersist
    public void prePersist() {
        if (dataEntrada == null){
            dataEntrada = LocalDateTime.now();
        }
    }
}
