package com.example.mastersys.dto;

import com.example.mastersys.domain.FaturaMatricula;
import com.example.mastersys.domain.enums.StatusFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FaturaMatriculaResponse(
        Long id,
        Long matriculaId,
        LocalDate dataVencimento,
        BigDecimal valor,
        LocalDateTime dataPagamento,
        LocalDate dataCancelamento,
        StatusFatura status
) {

    public static FaturaMatriculaResponse fromEntity(FaturaMatricula entidade) {
        return new FaturaMatriculaResponse(
                entidade.getId(),
                entidade.getMatricula().getId(),
                entidade.getDataVencimento(),
                entidade.getValor(),
                entidade.getDataPagamento(),
                entidade.getDataCancelamento(),
                entidade.getStatus()
        );
    }
}
