package com.example.mastersys.dto;

import com.example.mastersys.domain.Modalidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ModalidadeRequest(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres.")
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
