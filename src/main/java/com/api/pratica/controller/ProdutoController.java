package com.api.pratica.controller;

import com.api.pratica.enums.StatusProduto;
import com.api.pratica.service.ProdutoService;
import com.api.pratica.models.Produto;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import java.net.URI;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> save(@Valid @RequestBody Produto produto) {
        Produto salvo = service.save(produto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(salvo);
    }

    @GetMapping
    public List<Produto> list(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String startingWith,
            @RequestParam(required = false) String endingWith,
            @RequestParam(required = false) String containing,
            @RequestParam(required = false) BigDecimal valor,
            @RequestParam(required = false) StatusProduto status
    ) {
        return service.search(nome, startingWith, endingWith, containing, valor,status);
    }

    @PutMapping("/{id}")
    public Produto update(@Valid @RequestBody Produto produto, @PathVariable Long id) {
        return service.update(produto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status")
    public List<Produto> status(
        @RequestParam(required = false) StatusProduto status
    ) {
        return service.status(status);
    }

    @GetMapping("/preco")
    public List<Produto> preco(
            @RequestParam(required = false) BigDecimal valor,
            @RequestParam(required = false) BigDecimal valorMaiorQue,
            @RequestParam(required = false) BigDecimal valorMenorQue
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
    public BigDecimal valorTotal() { return service.findTotalValue();}
}
