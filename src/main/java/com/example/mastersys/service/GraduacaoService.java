package com.example.mastersys.service;

import com.example.mastersys.domain.Graduacao;
import com.example.mastersys.domain.Modalidade;
import com.example.mastersys.dto.GraduacaoRequest;
import com.example.mastersys.dto.GraduacaoResponse;
import com.example.mastersys.exception.RecursoNaoEncontradoException;
import com.example.mastersys.exception.RegraNegocioException;
import com.example.mastersys.repository.GraduacaoRepository;
import com.example.mastersys.repository.ModalidadeRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GraduacaoService {

    private final GraduacaoRepository graduacaoRepository;
    private final ModalidadeRepository modalidadeRepository;

    public GraduacaoService(GraduacaoRepository graduacaoRepository, ModalidadeRepository modalidadeRepository) {
        this.graduacaoRepository = graduacaoRepository;
        this.modalidadeRepository = modalidadeRepository;
    }

    public GraduacaoResponse cadastrar(@NotBlank @Size(max = 100) GraduacaoRequest request){
        Modalidade modalidade = modalidadeRepository.findById(request.modalidadeId())
                .orElseThrow(() -> new RegraNegocioException("Modalidade não encontrada"));

        if (graduacaoRepository.existsByNomeAndModalidade_Id(request.nome(), request.modalidadeId())){
            throw new RegraNegocioException("Ja existe uma graduacao com esse nome nesta modalidade");
        }

        Graduacao graduacao = request.toEntity(modalidade);
        Graduacao graduacaoSalva = graduacaoRepository.save(graduacao);

        return GraduacaoResponse.fromEntity(graduacaoSalva);
    }

    public Page<GraduacaoResponse> listar(Long modalidadeId, Pageable pageable){
        if (modalidadeId == null){
            return graduacaoRepository.findAll(pageable)
                    .map(GraduacaoResponse::fromEntity);
        }

        return graduacaoRepository.findByModalidade_Id(modalidadeId, pageable)
                .map(GraduacaoResponse::fromEntity);
    }

    public GraduacaoResponse buscarPorId(Long id){
        Graduacao graduacao = buscarEntidadePorId(id);
        return GraduacaoResponse.fromEntity(graduacao);
    }

    private Graduacao buscarEntidadePorId(Long id) {
        return graduacaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Graduação não encontrada para o ID " + id + "."));
    }
}
