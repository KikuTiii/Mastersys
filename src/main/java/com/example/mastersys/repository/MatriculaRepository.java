package com.example.mastersys.repository;

import com.example.mastersys.domain.Matricula;
import com.example.mastersys.dto.MatriculaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    Page<Matricula> findByAluno_Id(Long alunoId, Pageable pageable);
}
