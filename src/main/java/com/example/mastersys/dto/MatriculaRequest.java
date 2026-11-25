package com.example.mastersys.dto;

import com.example.mastersys.domain.Aluno;
import com.example.mastersys.domain.Matricula;
import com.example.mastersys.domain.enums.StatusMatricula;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record MatriculaRequest(
        @NotNull(message = "O ID do aluno é obrigatório.")
        @Positive(message = "O ID do aluno deve ser maior que zero.")
        Long alunoId,
        LocalDate dataMatricula,
        @Min(value = 1, message = "O dia de vencimento deve ser no mínimo {value}.")
        @Max(value = 31, message = "O dia de vencimento deve ser no máximo {value}.")
        int diaVencimento,
        LocalDate dataEncerramento,
        StatusMatricula status
) {

    public Matricula toEntity(Aluno aluno) {
        Matricula entidade = new Matricula();
        preencher(entidade, aluno);
        return entidade;
    }

    public void preencher(Matricula entidade, Aluno aluno) {
        entidade.setAluno(aluno);
        if (dataMatricula != null) {
            entidade.setDataMatricula(dataMatricula);
        }
        entidade.setDiaVencimento(diaVencimento);
        entidade.setDataEncerramento(dataEncerramento);
        if (status != null) {
            entidade.setStatus(status);
        }
    }
}
