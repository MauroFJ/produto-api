package com.api.pratica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.pratica.models.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByNome(String nome);
    List<Produto> findByNomeStartingWith(String nome);
    List<Produto> findByNomeEndingWith(String nome);
    List<Produto> findByNomeContaining(String nome);
}
