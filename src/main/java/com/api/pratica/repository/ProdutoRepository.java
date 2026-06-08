package com.api.pratica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.pratica.models.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByNome(String nome);
    List<Produto> findByNomeStartingWith(String nome);
    List<Produto> findByNomeEndingWith(String nome);
    List<Produto> findByNomeContaining(String nome);

    List<Produto> findByStatus(String status);
    List<Produto> findByStatusIsNull();

    List<Produto> findByValor(Double valor);
    List<Produto> findByValorGreaterThan(Double valor);
    List<Produto> findByValorLessThan(Double valor);

    List<Produto> findByQuantidade(Integer quantidade);
    List<Produto> findByQuantidadeGreaterThan(Integer quantidade);
    List<Produto> findByQuantidadeLessThan(Integer quantidade);

    List<Produto> findByNomeAndValor(String nome, Double valor);
    List<Produto> findByNomeAndStatus(String nome, String status);
}
