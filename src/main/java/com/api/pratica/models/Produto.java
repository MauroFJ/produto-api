package com.api.pratica.models;

import com.api.pratica.enums.StatusProduto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Coloque um nome!")
    private String nome;

    @NotNull(message = "Informe um valor")
    @Positive(message = "O valor deve ser maior que zero")
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @PositiveOrZero(message = "O valor deve ser zero ou maior")
    private int quantidade;

    @Enumerated(EnumType.STRING)
    private StatusProduto status;

    public Produto() {}

    public Produto(String nome, int quantidade, BigDecimal valor, StatusProduto status) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public StatusProduto getStatus() {
        return status;
    }

    public void setStatus(StatusProduto status) {
        this.status = status;
    }
}
