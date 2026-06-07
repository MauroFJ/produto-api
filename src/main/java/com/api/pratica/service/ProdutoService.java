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
}
