package com.api.pratica.service;

import com.api.pratica.enums.StatusProduto;
import com.api.pratica.exceptions.ProdutoNotFoundException;
import com.api.pratica.models.Produto;
import com.api.pratica.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTests {

    @Mock
    private ProdutoRepository repository;

    @InjectMocks
    private ProdutoService service;

    @Test
    void delete_quandoNaoExistir_deveLancarExcecao() {
        when(repository.existsById(9999L)).thenReturn(false);

        assertThatThrownBy(() -> service.delete(9999L))
                .isInstanceOf(ProdutoNotFoundException.class);
        verify(repository, never()).deleteById(any());
    }

    @Test
    void delete_quandoExiste_deveChamarDeleteById() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void update_quandoNaoExiste_deveLancarExcecao() {
        Produto entrada = new Produto("RTX 3060", 1, new BigDecimal("100.00"), StatusProduto.DISPONIVEL);
        when(repository.existsById(9999L)).thenReturn(false);

        assertThatThrownBy(() -> service.update(entrada, 9999L))
                .isInstanceOf(ProdutoNotFoundException.class);
        verify(repository, never()).save(any());
    }

    @Test
    void update_quandoExistir_deveSetarIdESalvar() {
        Produto entrada = new Produto("RTX 3060", 1, new BigDecimal("100.00"), StatusProduto.DISPONIVEL);
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(any(Produto.class))).thenReturn(entrada);

        Produto resultado = service.update(entrada, 1L);

        assertThat(entrada.getId()).isEqualTo(1L);
        verify(repository).save(entrada);
        assertThat(resultado).isSameAs(entrada);
    }
}
