package com.example.mastersys.dto;

import com.example.mastersys.domain.Graduacao;
import com.example.mastersys.domain.Modalidade;

public record GraduacaoRequest(
        Long modalidadeId,
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
