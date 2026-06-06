package com.api.pratica.controller;

import com.api.pratica.service.ProdutoService;
import com.api.pratica.models.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public Produto save(@RequestBody Produto produto) {
        return service.save(produto);
    }

    @GetMapping
    public List<Produto> listAll() {
        return service.listAll();
    }

    @PutMapping("/{id}")
    public Produto update(@RequestBody Produto produto, @PathVariable Long id) {
        return service.update(produto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
