package com.example.mastersys.dto;

import com.example.mastersys.domain.Assiduidade;

import java.time.LocalDateTime;

public record AssiduidadeResponse(
        Long id,
        Long matriculaId,
        LocalDateTime dataEntrada,
        LocalDateTime dataSaida
) {

    public static AssiduidadeResponse fromEntity(Assiduidade entidade) {
        return new AssiduidadeResponse(
                entidade.getId(),
                entidade.getMatricula().getId(),
                entidade.getDataEntrada(),
                entidade.getDataSaida()
        );
    }
}
