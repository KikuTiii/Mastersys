package com.example.mastersys.dto;

import com.example.mastersys.domain.Graduacao;
import com.example.mastersys.domain.Modalidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record GraduacaoRequest(
        @NotNull(message = "O ID da modalidade é obrigatório.")
        @Positive(message = "O ID da modalidade deve ser maior que zero.")
        Long modalidadeId,
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres.")
        String nome
) {

    public Graduacao toEntity(Modalidade modalidade) {
        Graduacao entidade = new Graduacao();
        preencher(entidade, modalidade);
        return entidade;
    }

    public void preencher(Graduacao entidade, Modalidade modalidade) {
        entidade.setModalidade(modalidade);
        entidade.setNome(nome);
    }
}
