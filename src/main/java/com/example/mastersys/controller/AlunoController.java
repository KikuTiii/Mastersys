package com.example.mastersys.controller;

import com.example.mastersys.dto.AlunoRequest;
import com.example.mastersys.dto.AlunoResponse;
import com.example.mastersys.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponse> cadastrar(
            @Valid @RequestBody AlunoRequest request, UriComponentsBuilder uriBuilder) {
        AlunoResponse aluno = alunoService.cadastrar(request);
        URI location = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.id()).toUri();
        return ResponseEntity.created(location).body(aluno);
    }

    @GetMapping
    public PagedModel<AlunoResponse> listar(@PageableDefault(sort = "id") Pageable pageable) {
        return new PagedModel<>(alunoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public AlunoResponse buscarPorId(@PathVariable("id") Long id) {
        return alunoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AlunoResponse atualizar(@PathVariable("id") Long id, @Valid @RequestBody AlunoRequest request) {
        return alunoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        alunoService.exclur(id);
        return ResponseEntity.noContent().build();
    }
}
