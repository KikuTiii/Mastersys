package com.example.mastersys.controller;

import com.example.mastersys.dto.MatriculaCadastroRequest;
import com.example.mastersys.dto.MatriculaResponse;
import com.example.mastersys.service.MatriculaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MatriculaResponse cadastrar(@Valid @RequestBody MatriculaCadastroRequest request){
        return matriculaService.cadastrar(request);
    }

    @GetMapping
    public PagedModel<MatriculaResponse> listar(
            @RequestParam(name = "alunoId", required = false) Long alunoId,
            @PageableDefault(sort = "id") Pageable pageable
    ){
        return new PagedModel<>(matriculaService.listar(alunoId, pageable));
    }

    @GetMapping("/{id}")
    public MatriculaResponse buscarPorId(@PathVariable("id") Long id){ return matriculaService.buscarPorId(id); }
}
