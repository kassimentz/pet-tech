package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class ProdutoRepository implements IProdutoRepository {

    private static Set<Produto> produtos;

    static {
        produtos = new LinkedHashSet<>();

        Produto produto1 = new Produto("Produto 1", "Descricao 1", "UrlImagem 1", new BigDecimal(19.99));
        Produto produto2 = new Produto("Produto 2", "Descricao 2", "UrlImagem 2", new BigDecimal(20.99));

        produtos.add(produto1);
        produtos.add(produto2);
    }
    @Override
    public Optional<Produto> findById(UUID id) {
        return produtos.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Set<Produto> findAll() {
        return produtos;
    }

    @Override
    public Produto save(Produto produto) {
        produtos.add(produto);
        return produto;
    }

    @Override
    public Produto update(UUID id, Produto produto) {
        var produtoASerAtualizado = produtos.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        produtoASerAtualizado.setNome(produto.getNome());
        produtoASerAtualizado.setDescricao(produto.getDescricao());
        produtoASerAtualizado.setPreco(produto.getPreco());
        produtoASerAtualizado.setUrlImagem(produto.getUrlImagem());
        return produtoASerAtualizado;
    }

    @Override
    public void delete(UUID id) {
        produtos.removeIf(produto -> produto.getId().equals(id));
    }
}
