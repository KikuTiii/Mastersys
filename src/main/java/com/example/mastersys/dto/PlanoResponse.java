package com.example.mastersys.dto;

import com.example.mastersys.domain.Plano;

import java.math.BigDecimal;

public record PlanoResponse(
        Long id,
        Long modalidadeId,
        String nome,
        BigDecimal valorMensal,
        boolean ativo
) {

    public static PlanoResponse fromEntity(Plano entidade) {
        return new PlanoResponse(
                entidade.getId(),
                entidade.getModalidade().getId(),
                entidade.getNome(),
                entidade.getValorMensal(),
                entidade.isAtivo()
        );
    }
}
