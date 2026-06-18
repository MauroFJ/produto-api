package com.api.pratica.specifications;

import com.api.pratica.enums.StatusProduto;
import com.api.pratica.models.Produto;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProdutoSpecs {

    public static Specification<Produto> nomeIgual(String nome) {
        return (root, query, cb) -> cb.equal(root.get("nome"), nome);
    }
    public static Specification<Produto> nomeComecaCom(String prefixo) {
        return (root, query, cb) -> cb.like(root.get("nome"), prefixo + "%");
    }
    public static Specification<Produto> nomeTerminaCom(String sufixo) {
        return (root, query, cb) -> cb.like(root.get("nome"), "%" + sufixo);
    }
    public static Specification<Produto> nomeContem(String trecho) {
        return (root, query, cb) -> cb.like(root.get("nome"), "%" + trecho + "%");
    }
    public static Specification<Produto> statusIgual(StatusProduto status) {
        return (root, query, cb) -> cb.equal(root.get("status"), status);
    }
    public static Specification<Produto> valorIgual(BigDecimal valor) {
        return (root, query, cb) -> cb.equal(root.get("valor"), valor);
    }
    public static Specification<Produto> valorMaiorQue(BigDecimal valor) {
        return (root, query, cb) -> cb.greaterThan(root.get("valor"), valor);
    }
    public static Specification<Produto> valorMenorQue(BigDecimal valor) {
        return (root, query, cb) -> cb.lessThan(root.get("valor"), valor);
    }
    public static Specification<Produto> quantidadeIgual(Integer quantidade) {
        return (root, query, cb) -> cb.equal(root.get("quantidade"), quantidade);
    }
    public static Specification<Produto> quantidadeMaiorQue(Integer quantidade) {
        return (root, query, cb) -> cb.greaterThan(root.get("quantidade"), quantidade);
    }
    public static Specification<Produto> quantidadeMenorQue(Integer quantidade) {
        return (root, query, cb) -> cb.lessThan(root.get("quantidade"), quantidade);
    }
}