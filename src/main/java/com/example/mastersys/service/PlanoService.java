package com.example.mastersys.service;

import com.example.mastersys.domain.Modalidade;
import com.example.mastersys.domain.Plano;
import com.example.mastersys.dto.PlanoFiltroRequest;
import com.example.mastersys.dto.PlanoRequest;
import com.example.mastersys.dto.PlanoResponse;
import com.example.mastersys.exception.RegraNegocioException;
import com.example.mastersys.repository.ModalidadeRepository;
import com.example.mastersys.repository.PlanoRepository;
import com.example.mastersys.specification.PlanoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;
    private final ModalidadeRepository modalidadeRepository;

    public PlanoService(PlanoRepository planoRepository, ModalidadeRepository modalidadeRepository) {
        this.planoRepository = planoRepository;
        this.modalidadeRepository = modalidadeRepository;
    }

    public PlanoResponse cadastrar(PlanoRequest request) {
        Modalidade modalidade = modalidadeRepository.findById(request.modalidadeId())
                .orElseThrow(() -> new RegraNegocioException("Modalidade não encontrada."));

        if (planoRepository.existsByNomeAndModalidade_Id(request.nome(), modalidade.getId())) {
            throw new RegraNegocioException("Já existe um plano com esse nome nesta modalidade.");
        }

        Plano plano = request.toEntity(modalidade);
        Plano planoSalvo = planoRepository.save(plano);

        return PlanoResponse.fromEntity(planoSalvo);
    }

    public Page<PlanoResponse> listar (PlanoFiltroRequest filtro, Pageable pageable){
        return planoRepository.findAll(PlanoSpecification.comFiltros(filtro),
                pageable).map(PlanoResponse::fromEntity);
    }

    public PlanoResponse buscarPorId(Long id){
        Plano plano = bucarEntidadePorId(id);
        return PlanoResponse.fromEntity(plano);
    }

    private Plano bucarEntidadePorId(Long id) {
        return planoRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Plano não encontrado"));
    }
}
