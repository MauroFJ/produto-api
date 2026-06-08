package com.api.pratica.service;

import com.api.pratica.repository.ProdutoRepository;
import com.api.pratica.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> search(String name, String startingWith, String endingWith, String containing) {
        if(name != null) {
            return repository.findByNome(name);
        }
        if(startingWith != null) {
            return repository.findByNomeStartingWith(startingWith);
        }
        if(endingWith != null) {
            return repository.findByNomeEndingWith(endingWith);
        }
        if(containing != null) {
            return repository.findByNomeContaining(containing);
        }

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

    public List<Produto> preco(Double valor, Double valorMaiorQue, Double valorMenorQue) {
        if(valor != null) return repository.findByValor(valor);
        if(valorMaiorQue != null) return repository.findByValorGreaterThan(valorMaiorQue);
        if(valorMenorQue != null) return repository.findByValorLessThan(valorMenorQue);

        return repository.findAll();
    }
}
