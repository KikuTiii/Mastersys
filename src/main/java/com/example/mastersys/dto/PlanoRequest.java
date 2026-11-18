package com.example.mastersys.dto;

import com.example.mastersys.domain.Modalidade;
import com.example.mastersys.domain.Plano;

import java.math.BigDecimal;

public record PlanoRequest(
        Long modalidadeId,
        String nome,
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
