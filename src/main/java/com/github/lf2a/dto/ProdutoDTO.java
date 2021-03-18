package com.github.lf2a.dto;

import com.github.lf2a.domain.Produto;

import java.io.Serializable;

/**
 * <h1>ProdutoDTO.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 18/03/2021
 */
public class ProdutoDTO implements Serializable {

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        id = produto.getId();
        nome = produto.getNome();
        preco = produto.getPreco();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
