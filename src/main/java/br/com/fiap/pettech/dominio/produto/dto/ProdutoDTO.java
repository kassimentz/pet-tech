package br.com.fiap.pettech.dominio.produto.dto;

import br.com.fiap.pettech.dominio.produto.entity.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProdutoDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private String urlImagem;
    private BigDecimal preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(UUID id, String nome, String descricao, String urlImagem, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlImagem = urlImagem;
        this.preco = preco;
    }

    public ProdutoDTO(Produto entidade) {
        this.id = entidade.getId();
        this.nome = entidade.getNome();
        this.descricao = entidade.getDescricao();
        this.urlImagem = entidade.getUrlImagem();;
        this.preco = entidade.getPreco();
    }

    public UUID getId() {
        return id;
    }

    public ProdutoDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public ProdutoDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public ProdutoDTO setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public ProdutoDTO setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
        return this;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public ProdutoDTO setPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }
}