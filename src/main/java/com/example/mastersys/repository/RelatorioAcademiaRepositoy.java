package com.example.mastersys.repository;

import com.example.mastersys.domain.FaturaMatricula;
import com.example.mastersys.projection.AlunosPorCidadeProjection;
import com.example.mastersys.projection.FaturamentoMensalProjection;
import com.example.mastersys.projection.FaturasEmAbertoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface RelatorioAcademiaRepositoy extends Repository<FaturaMatricula, Long> {

    @Query(
            value = """
            SELECT
                    TO_CHAR(fatura.data_vencimento, 'YYYY-MM') AS mes,
                    SUM(fatura.valor) AS total
            FROM fatura_matriculas fatura
            WHERE fatura.status = 'PAGA'
            GROUP BY mes
            ORDER BY mes
            """,
            nativeQuery = true
    )
    List<FaturamentoMensalProjection> faturamentoMensal();


    @Query(
            value = """
            SELECT
                    cidade,
                    COUNT(*) AS quantidade
            FROM alunos
            GROUP BY cidade
            ORDER BY quantidade desc
            """,
            nativeQuery = true
    )
    List<AlunosPorCidadeProjection> alunosPorCidade();


    @Query(
            value = """
            SELECT
                    f.matricula_id AS "matriculaId",
                    a.nome AS "alunoNome",
                    f.data_vencimento AS "dataVencimento",
                    f.valor AS valor
            FROM fatura_matriculas f
            JOIN matriculas m ON m.id = f.matricula_id
            JOIN alunos a ON a.id = m.aluno_id
            WHERE f.status = 'ABERTA'
            ORDER BY f.data_vencimento desc
            """,
            nativeQuery = true
    )
    List<FaturasEmAbertoProjection> faturasEmAberto();
}
