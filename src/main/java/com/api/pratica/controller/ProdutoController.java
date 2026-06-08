package com.api.pratica.controller;

import com.api.pratica.service.ProdutoService;
import com.api.pratica.models.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;

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
            @RequestParam(required = false) String containing,
            @RequestParam(required = false) Double valor,
            @RequestParam(required = false) String status
    ) {
        return service.search(nome, startingWith, endingWith, containing, valor,status);
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
        @RequestParam(required = false) String status
    ) {
        return service.status(status);
    }

    @GetMapping("/preco")
    public List<Produto> preco(
            @RequestParam(required = false) Double valor,
            @RequestParam(required = false) Double valorMaiorQue,
            @RequestParam(required = false) Double valorMenorQue
    ) {
        return service.preco(valor, valorMaiorQue, valorMenorQue);
    }

    @GetMapping("/quantidade")
    public List<Produto> quantidade(
            @RequestParam(required = false) Integer quantidade,
            @RequestParam(required = false) Integer quantidadeMaiorQue,
            @RequestParam(required = false) Integer quantidadeMenorQue
    ) {
        return service.quantidade(quantidade, quantidadeMaiorQue, quantidadeMenorQue);
    }

    @GetMapping("/quantidadeDeProdutos")
    public Long count() { return service.count();}

    @GetMapping("/valorTotal")
    public Double valorTotal() { return service.findTotalValue();}
}
