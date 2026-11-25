package com.example.mastersys.controller;

import com.example.mastersys.projection.AlunosPorCidadeProjection;
import com.example.mastersys.projection.FaturamentoMensalProjection;
import com.example.mastersys.projection.FaturasEmAbertoProjection;
import com.example.mastersys.repository.RelatorioAcademiaRepositoy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioAcademiaController {

    private final RelatorioAcademiaRepositoy relatorioAcademiaRepositoy;

    public RelatorioAcademiaController(RelatorioAcademiaRepositoy repository) {
        this.relatorioAcademiaRepositoy = repository;
    }

    @GetMapping("/faturamento-mensal")
    public List<FaturamentoMensalProjection> faturamentoMensal() {
        return relatorioAcademiaRepositoy.faturamentoMensal();
    }

    @GetMapping("/alunos-por-cidade")
    public List<AlunosPorCidadeProjection> alunosPorCidade() {
        return relatorioAcademiaRepositoy.alunosPorCidade();
    }

    @GetMapping("/faturas-em-aberto")
    public List<FaturasEmAbertoProjection> faturasEmAberto() {
        return relatorioAcademiaRepositoy.faturasEmAberto();
    }
}
