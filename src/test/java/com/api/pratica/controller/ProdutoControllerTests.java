package com.api.pratica.controller;

import com.api.pratica.enums.StatusProduto;
import com.api.pratica.exceptions.ProdutoNotFoundException;
import com.api.pratica.models.Produto;
import com.api.pratica.service.ProdutoService;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService service;

    @Test
    void save_valido_deveRetornar201ComLocation() throws Exception {
        Produto salvo = new Produto("RTX 3060", 44, new BigDecimal("2550.90"), StatusProduto.DISPONIVEL);
        salvo.setId(1L);
        when(service.save(any(Produto.class))).thenReturn(salvo);

        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"nome":"RTX 3060","quantidade":44,"valor":2550.90,"status":"DISPONIVEL"}
                    """))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", containsString("/produtos/1")))
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.nome").value("RTX 3060"));
    }

    @Test
    void save_comNomeEmBranco_deveRetornar400() throws Exception {
        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"nome":"","quantidade":1,"valor":100,"status":"DISPONIVEL"}        
                    """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("Coloque um nome!"));
        verify(service, never()).save(any());
    }

    @Test
    void findById_existente_deveRetornar200() throws Exception {
        Produto p = new Produto("RTX 3060", 44, new BigDecimal("2550.90"), StatusProduto.DISPONIVEL);
        p.setId(1L);
        when(service.findById(1L)).thenReturn(Optional.of(p));

        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("RTX 3060"));
    }

    @Test
    void findById_inexistente_deveRetornar404() throws Exception {
        when(service.findById(9999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/produtos/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_inexistente_deveRetornar404() throws Exception {
        doThrow(new ProdutoNotFoundException(9999L)).when(service).delete(9999L);

        mockMvc.perform(delete("/produtos/9999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.ERRO").value("Produto não encontrado com o id 9999"));
    }

}
