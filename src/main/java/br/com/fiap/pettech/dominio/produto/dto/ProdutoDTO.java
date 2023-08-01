package br.com.fiap.pettech.dominio.produto.dto;

import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProdutoDTO {
    private UUID id;

    @NotBlank(message = "Nome Obrigatório")
    private String nome;
    private String descricao;
    private String urlImagem;

    private List<CategoriaDTO> categorias = new ArrayList<>();

    @Positive(message = "O valor do produto deve ser positivo")
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

    public ProdutoDTO(Produto produto, Set<Categoria> categorias) {
        this(produto);
        categorias.forEach(categoria -> this.categorias.add(new CategoriaDTO(categoria)));
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

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }
}
