package com.example.mastersys.domain;

import com.example.mastersys.domain.enums.StatusMatricula;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    @Column(name = "data_matricula", nullable = false)
    private LocalDate dataMatricula = LocalDate.now();

    @Column(name = "dia_vencimento", nullable = false)
    private int diaVencimento;

    @Column(name = "data_encerramento")
    private LocalDate dataEncerramento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusMatricula status = StatusMatricula.ATIVA;


    @PrePersist
    public void prePersist(){
        if (dataMatricula == null){
            dataMatricula = LocalDate.now();
        }
    }

}
