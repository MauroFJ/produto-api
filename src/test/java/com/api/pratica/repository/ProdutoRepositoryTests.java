package com.api.pratica.repository;

import com.api.pratica.enums.StatusProduto;
import com.api.pratica.models.Produto;
import com.api.pratica.specifications.ProdutoSpecs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.test.context.ActiveProfiles;

@DataJpaTest(showSql = false)
@ActiveProfiles("test")
public class ProdutoRepositoryTests {
    @Autowired
    private ProdutoRepository repository;

    @BeforeEach
    void prepararDados() {
        repository.save(new Produto("RTX 3060", 44, new BigDecimal("2550.90"), StatusProduto.DISPONIVEL));
        repository.save(new Produto("Processador I7 12gen", 12, new BigDecimal("2200.50"), StatusProduto.DISPONIVEL));
        repository.save(new Produto("Headset", 7, new BigDecimal("350.00"), StatusProduto.INDISPONIVEL));
    }

    @Test
    void semFiltro_deveTrazerTodos() {
        List<Produto> resultado = repository.findAll(Specification.unrestricted());

        assertThat(resultado).hasSize(3);
    }

    @Test
    void valorMenorQue_deveTrazerApenasOsMaisBaratos() {
        List<Produto> resultado = repository.findAll(ProdutoSpecs.valorMenorQue(new BigDecimal("1000")));

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Headset");
    }

    @Test
    void nomeContem_deveFiltrarPorTrechoDoNome(){
        List<Produto> resultado = repository.findAll(ProdutoSpecs.nomeContem("o"));

        assertThat(resultado).hasSize(1);
        assertThat(resultado.get(0).getNome()).isEqualTo("Processador I7 12gen");
    }

    @Test
    void specsCombinadas_devemAplicarAnd() {
        Specification<Produto> spec = ProdutoSpecs.statusIgual(StatusProduto.DISPONIVEL)
                .and(ProdutoSpecs.valorMaiorQue(new BigDecimal("2000")));

        List<Produto> resultado = repository.findAll(spec);

        assertThat(resultado)
                .extracting(Produto::getNome)
                .containsExactlyInAnyOrder("RTX 3060", "Processador I7 12gen");
    }
}
