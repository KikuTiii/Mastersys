package com.example.mastersys.controller;

import com.example.mastersys.dto.GraduacaoRequest;
import com.example.mastersys.dto.GraduacaoResponse;
import com.example.mastersys.service.GraduacaoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/graduacoes")
public class GraduacaoController {

    private final GraduacaoService graduacaoService;

    public GraduacaoController(GraduacaoService graduacaoService) {
        this.graduacaoService = graduacaoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public GraduacaoResponse cadastrar(@Valid @RequestBody GraduacaoRequest request){
        return graduacaoService.cadastrar(request);
    }

    @GetMapping
    public PagedModel<GraduacaoResponse> listar(
            @RequestParam(name = "modalidadeId", required = false) Long modalidadeId,
            @PageableDefault(sort = "id")Pageable pageable){
        return new PagedModel<>(graduacaoService.listar(modalidadeId, pageable));
    }

    @GetMapping("/{id}")
    public GraduacaoResponse buscarPorId(@PathVariable("id") Long id){
        return graduacaoService.buscarPorId(id);
    }
}
