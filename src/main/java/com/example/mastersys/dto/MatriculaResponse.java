package com.example.mastersys.dto;

import com.example.mastersys.domain.Matricula;
import com.example.mastersys.domain.enums.StatusMatricula;

import java.time.LocalDate;

public record MatriculaResponse(
        Long id,
        Long alunoId,
        LocalDate dataMatricula,
        int diaVencimento,
        LocalDate dataEncerramento,
        StatusMatricula status
) {

    public static MatriculaResponse fromEntity(Matricula entidade) {
        return new MatriculaResponse(
                entidade.getId(),
                entidade.getAluno().getId(),
                entidade.getDataMatricula(),
                entidade.getDiaVencimento(),
                entidade.getDataEncerramento(),
                entidade.getStatus()
        );
    }
}
