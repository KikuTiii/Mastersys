package com.example.mastersys.repository;

import com.example.mastersys.domain.Plano;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlanoRepository extends JpaRepository<Plano, Long>, JpaSpecificationExecutor<Plano> {

    boolean existsByNomeAndModalidade_Id(@NotBlank(message = "O nome é obrigatório.") @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres.") String nome, Long id);
}
