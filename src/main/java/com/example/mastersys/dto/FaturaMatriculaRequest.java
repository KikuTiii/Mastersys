package com.example.mastersys.dto;

import com.example.mastersys.domain.FaturaMatricula;
import com.example.mastersys.domain.Matricula;
import com.example.mastersys.domain.enums.StatusFatura;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FaturaMatriculaRequest(
        Long matriculaId,
        LocalDate dataVencimento,
        BigDecimal valor,
        LocalDateTime dataPagamento,
        LocalDate dataCancelamento,
        StatusFatura status
) {

    public FaturaMatricula toEntity(Matricula matricula) {
        FaturaMatricula entidade = new FaturaMatricula();
        preencher(entidade, matricula);
        return entidade;
    }

    public void preencher(FaturaMatricula entidade, Matricula matricula) {
        entidade.setMatricula(matricula);
        entidade.setDataVencimento(dataVencimento);
        entidade.setValor(valor);
        entidade.setDataPagamento(dataPagamento);
        entidade.setDataCancelamento(dataCancelamento);
        if (status != null) {
            entidade.setStatus(status);
        }
    }
}
