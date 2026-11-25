package com.example.mastersys.service;

import com.example.mastersys.domain.Modalidade;
import com.example.mastersys.dto.ModalidadeRequest;
import com.example.mastersys.dto.ModalidadeResponse;
import com.example.mastersys.repository.ModalidadeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ModalidadeService {

    private final ModalidadeRepository modalidadeRepository;

    public ModalidadeService(ModalidadeRepository modalidadeRepository) {
        this.modalidadeRepository = modalidadeRepository;
    }

    public ModalidadeResponse cadastrar(ModalidadeRequest request){
        Modalidade modalidade = request.toEntity();
        Modalidade salva = modalidadeRepository.save(modalidade);
        return ModalidadeResponse.fromEntity(salva);
    }

    public Page<ModalidadeResponse> listar(Pageable pageable){
        return modalidadeRepository.findAll(pageable)
                .map(ModalidadeResponse::fromEntity);
    }
}
