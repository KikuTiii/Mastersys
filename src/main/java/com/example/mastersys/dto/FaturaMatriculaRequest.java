package com.example.mastersys.dto;

import com.example.mastersys.domain.FaturaMatricula;
import com.example.mastersys.domain.Matricula;
import com.example.mastersys.domain.enums.StatusFatura;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record FaturaMatriculaRequest(
        @NotNull(message = "O ID da matrícula é obrigatório.")
        @Positive(message = "O ID da matrícula deve ser maior que zero.")
        Long matriculaId,
        @NotNull(message = "A data de vencimento é obrigatória.")
        LocalDate dataVencimento,
        @NotNull(message = "O valor é obrigatório.")
        @PositiveOrZero(message = "O valor deve ser maior ou igual a zero.")
        @Digits(integer = 8, fraction = 2, message = "O valor deve ter até {integer} dígitos inteiros e {fraction} casas decimais.")
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
