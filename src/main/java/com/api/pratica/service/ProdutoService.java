package com.api.pratica.service;

import com.api.pratica.enums.StatusProduto;
import com.api.pratica.exceptions.ProdutoNotFoundException;
import com.api.pratica.repository.ProdutoRepository;
import com.api.pratica.specifications.ProdutoSpecs;
import com.api.pratica.models.Produto;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> search(
            String name, String startingWith, String endingWith, String containing,
            StatusProduto status,
            BigDecimal value, BigDecimal valueGreaterThan, BigDecimal valueLessThan,
            Integer quantity, Integer quantityGreaterThan, Integer quantityLessThan) {

        Specification<Produto> spec = Specification.unrestricted();

        if (name != null)                      spec = spec.and(ProdutoSpecs.nomeIgual(name));
        if (startingWith != null)              spec = spec.and(ProdutoSpecs.nomeComecaCom(startingWith));
        if (endingWith != null)                spec = spec.and(ProdutoSpecs.nomeTerminaCom(endingWith));
        if (containing != null)                spec = spec.and(ProdutoSpecs.nomeContem(containing));
        if (status != null)                    spec = spec.and(ProdutoSpecs.statusIgual(status));
        if (value != null)                     spec = spec.and(ProdutoSpecs.valorIgual(value));
        if (valueGreaterThan != null)          spec = spec.and(ProdutoSpecs.valorMaiorQue(valueGreaterThan));
        if (valueLessThan != null)             spec = spec.and(ProdutoSpecs.valorMenorQue(valueLessThan));
        if (quantity != null)                  spec = spec.and(ProdutoSpecs.quantidadeIgual(quantity));
        if (quantityGreaterThan != null)       spec = spec.and(ProdutoSpecs.quantidadeMaiorQue(quantityGreaterThan));
        if (quantityLessThan != null)          spec = spec.and(ProdutoSpecs.quantidadeMenorQue(quantityLessThan));

        return repository.findAll(spec);
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(Produto produto, Long id) {
        if(repository.existsById(id)) {
            produto.setId(id);
            return repository.save(produto);
        }else {
            throw new ProdutoNotFoundException(id);
        }
    }

    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ProdutoNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public Optional<Produto> findById(Long id) {
        return repository.findById(id);
    }

    public BigDecimal findTotalValue() { return repository.findTotalValor();}
    public Long count() { return repository.count();}
}
