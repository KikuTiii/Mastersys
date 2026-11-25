package com.example.mastersys.dto;

import com.example.mastersys.domain.Graduacao;
import com.example.mastersys.domain.Matricula;
import com.example.mastersys.domain.MatriculaModalidade;
import com.example.mastersys.domain.Modalidade;
import com.example.mastersys.domain.Plano;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record MatriculaModalidadeRequest(
        @NotNull(message = "O ID da matrícula é obrigatório.")
        @Positive(message = "O ID da matrícula deve ser maior que zero.")
        Long matriculaId,
        @NotNull(message = "O ID da modalidade é obrigatório.")
        @Positive(message = "O ID da modalidade deve ser maior que zero.")
        Long modalidadeId,
        @NotNull(message = "O ID da graduação é obrigatório.")
        @Positive(message = "O ID da graduação deve ser maior que zero.")
        Long graduacaoId,
        @NotNull(message = "O ID do plano é obrigatório.")
        @Positive(message = "O ID do plano deve ser maior que zero.")
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
