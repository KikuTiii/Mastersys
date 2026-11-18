package com.example.mastersys.dto;

import com.example.mastersys.domain.Aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AlunoResponse(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String sexo,
        String celular,
        String email,
        String cidade,
        String estado,
        String cep,
        LocalDateTime criadoEm
) {

    public static AlunoResponse fromEntity(Aluno entidade) {
        return new AlunoResponse(
                entidade.getId(),
                entidade.getNome(),
                entidade.getDataNascimento(),
                entidade.getSexo(),
                entidade.getCelular(),
                entidade.getEmail(),
                entidade.getCidade(),
                entidade.getEstado(),
                entidade.getCep(),
                entidade.getCriadoEm()
        );
    }
}
