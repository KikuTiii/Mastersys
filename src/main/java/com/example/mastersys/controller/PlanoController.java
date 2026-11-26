package com.example.mastersys.controller;

import com.example.mastersys.dto.PlanoFiltroRequest;
import com.example.mastersys.dto.PlanoRequest;
import com.example.mastersys.dto.PlanoResponse;
import com.example.mastersys.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PlanoResponse cadastrar(@Valid @RequestBody PlanoRequest request){
        return planoService.cadastrar(request);
    }

    @GetMapping
    public PagedModel<PlanoResponse> listar(
            @ModelAttribute PlanoFiltroRequest filtro,
            @PageableDefault(sort = "id") Pageable pageable){
        return new PagedModel<>(planoService.listar(filtro, pageable));
    }

    @GetMapping("/{id}")
    public PlanoResponse buscarPorId(@PathVariable("id") Long id){
        return planoService.buscarPorId(id);
    }
}
