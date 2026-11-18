package com.example.mastersys.dto;

import com.example.mastersys.domain.Aluno;
import com.example.mastersys.domain.Matricula;
import com.example.mastersys.domain.enums.StatusMatricula;

import java.time.LocalDate;

public record MatriculaRequest(
        Long alunoId,
        LocalDate dataMatricula,
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
