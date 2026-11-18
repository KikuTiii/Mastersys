package com.example.mastersys.dto;

import com.example.mastersys.domain.Graduacao;

public record GraduacaoResponse(
        Long id,
        Long modalidadeId,
        String nome
) {

    public static GraduacaoResponse fromEntity(Graduacao entidade) {
        return new GraduacaoResponse(
                entidade.getId(),
                entidade.getModalidade().getId(),
                entidade.getNome()
        );
    }
}
