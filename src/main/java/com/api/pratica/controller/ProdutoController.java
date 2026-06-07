package com.api.pratica.controller;

import com.api.pratica.service.ProdutoService;
import com.api.pratica.models.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<Produto> list(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String startingWith,
            @RequestParam(required = false) String endingWith,
            @RequestParam(required = false) String containing) {

        return service.search(nome, startingWith, endingWith, containing);
    }

    @PutMapping("/{id}")
    public Produto update(@RequestBody Produto produto, @PathVariable Long id) {
        return service.update(produto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Produto> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/status")
    public List<Produto> status(
        @RequestParam(required = false) String status) {
            return service.status(status);
    }
}
