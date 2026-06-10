package com.api.pratica.repository;

import com.api.pratica.enums.StatusProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.pratica.models.Produto;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    List<Produto> findByNome(String nome);
    List<Produto> findByNomeStartingWith(String nome);
    List<Produto> findByNomeEndingWith(String nome);
    List<Produto> findByNomeContaining(String nome);

    List<Produto> findByStatus(StatusProduto status);

    @Query("SELECT SUM(p.valor) FROM Produto p")
    BigDecimal findTotalValor();

    List<Produto> findByValor(BigDecimal valor);
    List<Produto> findByValorGreaterThan(BigDecimal valor);
    List<Produto> findByValorLessThan(BigDecimal valor);

    List<Produto> findByQuantidade(Integer quantidade);
    List<Produto> findByQuantidadeGreaterThan(Integer quantidade);
    List<Produto> findByQuantidadeLessThan(Integer quantidade);

    List<Produto> findByNomeAndValor(String nome, BigDecimal valor);
    List<Produto> findByNomeAndStatus(String nome, StatusProduto status);
}
