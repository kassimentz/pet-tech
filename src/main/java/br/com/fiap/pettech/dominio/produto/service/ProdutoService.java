package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private IProdutoRepository repo;

    public Collection<Produto> findAll() {
        return repo.findAll();
    }

    public Produto findById(UUID id) {
        var produto = repo.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto nao encontrado"));
        return produto;
    }

    public Produto save (Produto produto) {
        var produtoSaved = repo.save(produto);
        return produtoSaved;
    }

    public Produto update(UUID id, Produto produto) {
        try {
            Produto buscaProduto = repo.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setUrlImagem(produto.getUrlImagem());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto = repo.save(buscaProduto);
            return buscaProduto;
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Produto nao encontrado, id: " + id);
        }
    }

    public void delete(UUID id) {
        try {
            repo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Entidade nao encontrada com o id: "+id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("VIolacao de integridade da base");
        }

    }
}
