package com.example.mastersys.controller;

import com.example.mastersys.dto.ModalidadeRequest;
import com.example.mastersys.dto.ModalidadeResponse;
import com.example.mastersys.service.ModalidadeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/modalidades")
public class modalidadeController {

    private final ModalidadeService modalidadeService;

    public modalidadeController(ModalidadeService modalidadeService) {
        this.modalidadeService = modalidadeService;
    }

    @PostMapping
    public ResponseEntity<ModalidadeResponse> cadastrar(
            @Valid @RequestBody ModalidadeRequest request,
            UriComponentsBuilder uriBuilder) {
        ModalidadeResponse modalidade = modalidadeService.cadastrar(request);

        URI location = uriBuilder.path("/modalidades/{id}")
                .buildAndExpand(modalidade.id())
                .toUri();

        return ResponseEntity.created(location).body(modalidade);
    }

    @GetMapping
    public PagedModel<ModalidadeResponse> listar(
            @PageableDefault(sort = "id") Pageable pageable) {
        return new PagedModel<>(modalidadeService.listar(pageable));
    }
}
