package com.example.mastersys.dto;

import com.example.mastersys.domain.Modalidade;

public record ModalidadeResponse(
        Long id,
        String nome,
        boolean ativa
) {

    public static ModalidadeResponse fromEntity(Modalidade entidade) {
        return new ModalidadeResponse(
                entidade.getId(),
                entidade.getNome(),
                entidade.isAtiva()
        );
    }
}
