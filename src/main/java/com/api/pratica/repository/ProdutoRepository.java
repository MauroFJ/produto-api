package com.api.pratica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.pratica.models.Produto;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ProdutoRepository extends JpaRepository<Produto, Long>,
        JpaSpecificationExecutor<Produto> {

    @Query("SELECT SUM(p.valor) FROM Produto p")
    BigDecimal findTotalValor();
}