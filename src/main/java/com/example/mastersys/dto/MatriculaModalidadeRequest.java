package com.example.mastersys.dto;

import com.example.mastersys.domain.Graduacao;
import com.example.mastersys.domain.Matricula;
import com.example.mastersys.domain.MatriculaModalidade;
import com.example.mastersys.domain.Modalidade;
import com.example.mastersys.domain.Plano;

import java.time.LocalDate;

public record MatriculaModalidadeRequest(
        Long matriculaId,
        Long modalidadeId,
        Long graduacaoId,
        Long planoId,
        LocalDate dataInicio,
        LocalDate dataFim
) {

    public MatriculaModalidade toEntity(Matricula matricula, Modalidade modalidade, Graduacao graduacao, Plano plano) {
        MatriculaModalidade entidade = new MatriculaModalidade();
        preencher(entidade, matricula, modalidade, graduacao, plano);
        return entidade;
    }

    public void preencher(MatriculaModalidade entidade, Matricula matricula, Modalidade modalidade, Graduacao graduacao, Plano plano) {
        entidade.setMatricula(matricula);
        entidade.setModalidade(modalidade);
        entidade.setGraduacao(graduacao);
        entidade.setPlano(plano);
        if (dataInicio != null) {
            entidade.setDataInicio(dataInicio);
        }
        entidade.setDataFim(dataFim);
    }
}
