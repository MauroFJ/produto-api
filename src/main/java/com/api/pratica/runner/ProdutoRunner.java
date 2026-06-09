package com.api.pratica.runner;

import com.api.pratica.repository.ProdutoRepository;
import com.api.pratica.models.Produto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoRunner implements CommandLineRunner {
    private final ProdutoRepository repository;

    public ProdutoRunner(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Produto> produtos = repository.findAll();

        System.out.println("\n========= LISTA DE PRODUTOS ==================");
        System.out.printf("%-5s %-30s %5s.2f%n","ID", "NOME","PRECO");
        System.out.println("------------------------------------------------");

        if(produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado!");
        } else {
            for (Produto produto : produtos) {
                System.out.printf("%-5d %-30s %5.2f%n",
                        produto.getId(), produto.getNome(),produto.getValor());
            }
        }
        System.out.println("=================================================\n");
    }
}
