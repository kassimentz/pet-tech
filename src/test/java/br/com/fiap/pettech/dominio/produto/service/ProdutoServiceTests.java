package br.com.fiap.pettech.dominio.produto.service;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.repository.IProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTests {

    @InjectMocks
    private ProdutoService service;

    @Mock
    private IProdutoRepository repository;

    @Test
    public void findByIdDeveRetornarUmProdutoDTOAoBuscarPorID() {

        UUID id = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d594");

        Produto produto = new Produto();
        produto.setNome("teste");
        produto.setPreco(new BigDecimal(3000.55));
        produto.setDescricao("produto teste");
        produto.setUrlImagem("url imagem");
        produto.setId(null);

        Mockito.when(repository.findById((UUID)ArgumentMatchers.any())).thenReturn(Optional.of(produto));

        ProdutoDTO produtoDTO = service.findById(id);

        Assertions.assertNotNull(produtoDTO);

    }
}
