package com.api.pratica.service;

import com.api.pratica.repository.ProdutoRepository;
import com.api.pratica.models.Produto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> search(String name, String startingWith, String endingWith, String containing, BigDecimal valor, String status) {
        if(name != null && valor != null) return repository.findByNomeAndValor(name, valor);
        if(name != null && status != null) return repository.findByNomeAndStatus(name,status);
        if(startingWith != null) return repository.findByNomeStartingWith(startingWith);
        if(endingWith != null) return repository.findByNomeEndingWith(endingWith);
        if(containing != null) return repository.findByNomeContaining(containing);
        if(name != null) return repository.findByNome(name);

        return repository.findAll();
    }

    public List<Produto> list() {
        return repository.findAll();
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public Produto update(Produto produto, Long id) {
        if(repository.existsById(id)) {
            produto.setId(id);
            return repository.save(produto);
        }else {
            throw new RuntimeException("Produto não encontrado");
        }
    }

    public void delete(Long id) {
            repository.deleteById(id);
    }

    public Optional<Produto> findById(Long id) {
        return repository.findById(id);
    }

    public List<Produto> findByName(String name) {
        return repository.findByNome(name);
    }

    public List<Produto> status(String status) {
        if(status != null) {
            return repository.findByStatus(status);
        }
        return repository.findAll();
    }

    public List<Produto> preco(BigDecimal valor, BigDecimal valorMaiorQue, BigDecimal valorMenorQue) {
        if(valor != null) return repository.findByValor(valor);
        if(valorMaiorQue != null) return repository.findByValorGreaterThan(valorMaiorQue);
        if(valorMenorQue != null) return repository.findByValorLessThan(valorMenorQue);

        return repository.findAll();
    }

    public List<Produto> quantidade(Integer quantidade, Integer quantidadeMaiorQue, Integer quantidadeMenorQue) {
        if(quantidade != null) return repository.findByQuantidade(quantidade);
        if(quantidadeMaiorQue != null) return repository.findByQuantidadeGreaterThan(quantidadeMaiorQue);
        if(quantidadeMenorQue != null) return repository.findByQuantidadeLessThan(quantidadeMenorQue);

        return repository.findAll();
    }

    public BigDecimal findTotalValue() { return repository.findTotalValor();}
    public Long count() { return repository.count();}
}
