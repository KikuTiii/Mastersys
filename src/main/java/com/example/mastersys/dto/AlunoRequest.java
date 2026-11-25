package com.example.mastersys.dto;

import com.example.mastersys.domain.Aluno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlunoRequest(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 150, message = "O nome deve ter no máximo {max} caracteres.")
        String nome,
        @PastOrPresent(message = "A data de nascimento não pode estar no futuro.")
        LocalDate dataNascimento,
        @Pattern(regexp = "[MF]", message = "O sexo deve ser M ou F.")
        String sexo,
        @Size(max = 30, message = "O telefone deve ter no máximo {max} caracteres.")
        String telefone,
        @Size(max = 30, message = "O celular deve ter no máximo {max} caracteres.")
        String celular,
        @Email(message = "Informe um e-mail válido.")
        @Size(max = 150, message = "O e-mail deve ter no máximo {max} caracteres.")
        String email,
        String observacao,
        @Size(max = 150, message = "O endereço deve ter no máximo {max} caracteres.")
        String endereco,
        @Size(max = 20, message = "O número deve ter no máximo {max} caracteres.")
        String numero,
        @Size(max = 100, message = "O complemento deve ter no máximo {max} caracteres.")
        String complemento,
        @Size(max = 100, message = "O bairro deve ter no máximo {max} caracteres.")
        String bairro,
        @Size(max = 100, message = "A cidade deve ter no máximo {max} caracteres.")
        String cidade,
        @Size(max = 2, message = "O estado deve ter no máximo {max} caracteres.")
        String estado,
        @Size(max = 2, message = "O CEP deve ter no máximo {max} caracteres.")
        String cep
) {


    public Aluno toEntity(){
        Aluno aluno = new Aluno();
        preencher(aluno);
        return aluno;
    }

    public void preencher(Aluno aluno) {
        aluno.setNome(nome);
        aluno.setDataNascimento(dataNascimento);
        aluno.setSexo(sexo);
        aluno.setTelefone(telefone);
        aluno.setCelular(celular);
        aluno.setEmail(email);
        aluno.setObservacao(observacao);
        aluno.setEndereco(endereco);
        aluno.setNumero(numero);
        aluno.setComplemento(complemento);
        aluno.setBairro(bairro);
        aluno.setCidade(cidade);
        aluno.setEstado(estado);
        aluno.setCep(cep);
    }

}
