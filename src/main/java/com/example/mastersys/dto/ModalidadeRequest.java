package com.example.mastersys.dto;

import com.example.mastersys.domain.Modalidade;

public record ModalidadeRequest(
        String nome,
        Boolean ativa
) {

    public Modalidade toEntity() {
        Modalidade entidade = new Modalidade();
        preencher(entidade);
        return entidade;
    }

    public void preencher(Modalidade entidade) {
        entidade.setNome(nome);
        if (ativa != null) {
            entidade.setAtiva(ativa);
        }
    }
}
