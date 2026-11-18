package com.example.mastersys.dto;

import com.example.mastersys.domain.MatriculaModalidade;

import java.time.LocalDate;

public record MatriculaModalidadeResponse(
        Long id,
        Long matriculaId,
        Long modalidadeId,
        Long graduacaoId,
        Long planoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {

    public static MatriculaModalidadeResponse fromEntity(MatriculaModalidade entidade) {
        return new MatriculaModalidadeResponse(
                entidade.getId(),
                entidade.getMatricula().getId(),
                entidade.getModalidade().getId(),
                entidade.getGraduacao().getId(),
                entidade.getPlano().getId(),
                entidade.getDataInicio(),
                entidade.getDataFim()
        );
    }
}
