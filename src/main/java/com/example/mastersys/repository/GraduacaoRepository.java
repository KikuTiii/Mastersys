package com.example.mastersys.repository;

import com.example.mastersys.domain.Graduacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduacaoRepository extends JpaRepository<Graduacao, Long> {
    boolean existsByNomeAndModalidade_Id(@NotBlank(message = "O nome é obrigatório.") @Size(max = 100, message = "O nome deve ter no máximo {max} caracteres.") String nome, Long id);
    Page<Graduacao> findByModalidade_Id(Long modalidadeId, Pageable pageable);
}
