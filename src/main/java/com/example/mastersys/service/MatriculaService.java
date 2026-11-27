package com.example.mastersys.service;

import com.example.mastersys.domain.*;
import com.example.mastersys.dto.GraduacaoResponse;
import com.example.mastersys.dto.MatriculaCadastroRequest;
import com.example.mastersys.dto.MatriculaResponse;
import com.example.mastersys.exception.RecursoNaoEncontradoException;
import com.example.mastersys.exception.RegraNegocioException;
import com.example.mastersys.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final MatriculaModalidadeRepository matriculaModalidadeRepository;
    private final PlanoRepository planoRepository;
    private final ModalidadeRepository modalidadeRepository;
    private final AlunoRepository alunoRepository;
    private final GraduacaoRepository graduacaoRepository;

    public MatriculaService(MatriculaRepository matriculaRepository, MatriculaModalidadeRepository matriculaModalidadeRepository, PlanoRepository planoRepository, ModalidadeRepository modalidadeRepository, AlunoRepository alunoRepository, GraduacaoRepository graduacaoRepository) {
        this.matriculaRepository = matriculaRepository;
        this.matriculaModalidadeRepository = matriculaModalidadeRepository;
        this.planoRepository = planoRepository;
        this.modalidadeRepository = modalidadeRepository;
        this.alunoRepository = alunoRepository;
        this.graduacaoRepository = graduacaoRepository;
    }

    @Transactional
    public MatriculaResponse cadastrar(MatriculaCadastroRequest request) {
        Aluno aluno = alunoRepository.findById(request.alunoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno não encontrado."));

        Modalidade modalidade = modalidadeRepository.findById(request.modalidadeId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Modalidade não encontrada."));

        Plano plano = planoRepository.findById(request.planoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Plano não encontrado."));

        Graduacao graduacao = graduacaoRepository.findById(request.graduacaoId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Graduação não encontrada."));

        if (!modalidade.getId().equals(plano.getModalidade().getId())) {
            throw new RegraNegocioException("O plano não pertence à modalidade escolhida.");
        }

        if (!modalidade.getId().equals(graduacao.getModalidade().getId())) {
            throw new RegraNegocioException("A graduação não pertence à modalidade escolhida.");
        }

        Matricula matricula = new Matricula();
        matricula.setAluno(aluno);
        matricula.setDiaVencimento(request.diaVencimento());
        Matricula matriculaSalva = matriculaRepository.save(matricula);

        MatriculaModalidade vinculo = new MatriculaModalidade();
        vinculo.setMatricula(matriculaSalva);
        vinculo.setModalidade(modalidade);
        vinculo.setPlano(plano);
        vinculo.setGraduacao(graduacao);
        matriculaModalidadeRepository.save(vinculo);

        return MatriculaResponse.fromEntity(matriculaSalva);
    }

    public Page<MatriculaResponse> listar(Long alunoId, Pageable pageable){
        if (alunoId == null){
            return matriculaRepository.findAll(pageable)
                    .map(MatriculaResponse::fromEntity);
        }

        return matriculaRepository.findByAluno_Id(alunoId, pageable)
                .map(MatriculaResponse::fromEntity);
    }

    public MatriculaResponse buscarPorId(Long id){
        Matricula matricula = buscarEntidadePorId(id);
        return MatriculaResponse.fromEntity(matricula);
    }

    private Matricula buscarEntidadePorId(Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Matricula não encontrada para o ID " + id + "."));
    }
}
