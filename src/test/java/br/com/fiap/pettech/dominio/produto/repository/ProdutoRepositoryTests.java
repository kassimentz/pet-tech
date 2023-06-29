package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;

    private UUID idExistente;
    private UUID idNaoExistente;
    private PageRequest pageRequest;
    private long countTotalProdutos;
    private String nomeAtualizado;

    @BeforeEach
    void setUp() throws Exception {
        idExistente = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d594");
        idNaoExistente = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d550");
        pageRequest = PageRequest.of(0, 10);
        countTotalProdutos = 5L;
        nomeAtualizado = "Atualizacao Nome do Produto";
    }

    @Test
    public void findAllDeveRetornarListaDeObjetosCadastrados() {
        Page<Produto> produtos = produtoRepository.findAll(this.pageRequest);
        Assertions.assertEquals(countTotalProdutos, produtos.getTotalElements());
    }
    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        Optional<Produto> result = produtoRepository.findById(idExistente);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(idNaoExistente).orElseThrow(() -> new ControllerNotFoundException("Produto nao encontrado"));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {

        Produto produto = Factory.createProduto();
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);

        Assertions.assertNotNull(produtoSalvo.getId());
    }
    @Test
    public void saveDeveAtualizarObjetoCasoIdNaoSejaNull() {
        Produto produto = Factory.createProduto();
        produto.setId(idExistente);
        produto.setNome(nomeAtualizado);

        var produtoSalvo = produtoRepository.save(produto);

        Assertions.assertEquals(nomeAtualizado, produtoSalvo.getNome());
    }

    @Test
    public void deleteDeveDeletarObjetoCasoExista() {
        produtoRepository.deleteById(idExistente);
        Optional<Produto> result = produtoRepository.findById(idExistente);
        Assertions.assertFalse(result.isPresent());
    }
}
