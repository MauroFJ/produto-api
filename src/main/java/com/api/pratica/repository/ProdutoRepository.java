package com.api.pratica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.pratica.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
