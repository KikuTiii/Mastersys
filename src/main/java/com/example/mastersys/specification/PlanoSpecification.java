package com.example.mastersys.specification;

import com.example.mastersys.domain.Plano;
import com.example.mastersys.dto.PlanoFiltroRequest;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PlanoSpecification {

    public static Specification<Plano> comFiltros(PlanoFiltroRequest filtro) {
        return Specification
                .where(modalidadeIgual(filtro.modalidadeId()))
                .and(ativoIgual(filtro.ativo()));
    }

    private static Specification<Plano> modalidadeIgual(Long modalidadeId) {
        return (root, query, cb) -> {
            if (modalidadeId == null) {
                return null;
            }

            return cb.equal(root.get("modalidade").get("id"), modalidadeId);
        };
    }

    private static Specification<Plano> ativoIgual(Boolean ativo) {
        return (root, query, cb) -> {
            if (ativo == null) {
                return null;
            }

            return cb.equal(root.get("ativo"), ativo);
        };
    }
}