package com.example.mastersys.dto;

import com.example.mastersys.domain.Assiduidade;
import com.example.mastersys.domain.Matricula;

import java.time.LocalDateTime;

public record AssiduidadeRequest(
        Long matriculaId,
        LocalDateTime dataEntrada,
        LocalDateTime dataSaida
) {

    public Assiduidade toEntity(Matricula matricula) {
        Assiduidade entidade = new Assiduidade();
        preencher(entidade, matricula);
        return entidade;
    }

    public void preencher(Assiduidade entidade, Matricula matricula) {
        entidade.setMatricula(matricula);
        if (dataEntrada != null) {
            entidade.setDataEntrada(dataEntrada);
        }
        entidade.setDataSaida(dataSaida);
    }
}
