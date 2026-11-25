package com.example.mastersys.dto;

import com.example.mastersys.domain.Modalidade;
import com.example.mastersys.domain.Plano;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record PlanoRequest(
        @NotNull(message = "O ID da modalidade é obrigatório.")
        @Positive(message = "O ID da modalidade deve ser maior que zero.")
        Long modalidadeId,
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres.")
        String nome,
        @NotNull(message = "O valor mensal é obrigatório.")
        @PositiveOrZero(message = "O valor mensal deve ser maior ou igual a zero.")
        @Digits(integer = 8, fraction = 2, message = "O valor mensal deve ter até {integer} dígitos inteiros e {fraction} casas decimais.")
        BigDecimal valorMensal,
        Boolean ativo
) {

    public Plano toEntity(Modalidade modalidade) {
        Plano entidade = new Plano();
        preencher(entidade, modalidade);
        return entidade;
    }

    public void preencher(Plano entidade, Modalidade modalidade) {
        entidade.setModalidade(modalidade);
        entidade.setNome(nome);
        entidade.setValorMensal(valorMensal);
        if (ativo != null) {
            entidade.setAtivo(ativo);
        }
    }
}
