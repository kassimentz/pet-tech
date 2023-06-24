package br.com.fiap.pettech.dominio.produto.repository;

import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@DataJpaTest
public class ProdutoRepositoryTests {
    @Autowired
    private IProdutoRepository produtoRepository;

    @Test
    public void findByIdDeveRetornarObjetoCasoIdExista() {
        UUID id = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d594");
        Optional<Produto> result = produtoRepository.findById(id);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdDeveRetornarControllerNotFoundExceptionCasoIdNaoExista() {
        UUID id = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d550");

        Assertions.assertThrows(ControllerNotFoundException.class, () -> {
            produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto nao encontrado"));
        });
    }

    @Test
    public void saveDeveSalvarObjetoCasoIdSejaNull() {

        Produto produto = new Produto();
        produto.setNome("teste");
        produto.setPreco(new BigDecimal(3000.55));
        produto.setDescricao("produto teste");
        produto.setUrlImagem("url imagem");
        produto.setId(null);
        var produtoSalvo = produtoRepository.save(produto);

        Assertions.assertNotNull(produtoSalvo.getId());
    }
}
