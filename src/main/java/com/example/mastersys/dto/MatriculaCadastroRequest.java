package com.example.mastersys.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MatriculaCadastroRequest(
        @NotNull(message = "O ID do aluno é obrigatório.")
        @Positive(message = "O ID do aluno deve ser maior que zero.")
        Long alunoId,
        @NotNull(message = "O ID da modalidade é obrigatório.")
        @Positive(message = "O ID da modalidade deve ser maior que zero.")
        Long modalidadeId,
        @NotNull(message = "O ID do plano é obrigatório.")
        @Positive(message = "O ID do plano deve ser maior que zero.")
        Long planoId,
        @NotNull(message = "O ID da graduação é obrigatório.")
        @Positive(message = "O ID da graduação deve ser maior que zero.")
        Long graduacaoId,
        @NotNull(message = "O dia de vencimento é obrigatório.")
        @Min(value = 1, message = "O dia de vencimento deve ser no mínimo {value}.")
        @Max(value = 31, message = "O dia de vencimento deve ser no máximo {value}.")
        Integer diaVencimento
) {
}
