package br.com.fiap.pettech.dominio.produto.controller;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;
import br.com.fiap.pettech.dominio.produto.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.math.BigDecimal;
import java.util.UUID;

@WebMvcTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProdutoService produtoService;

    @Test
    public void findByIdDeveRetornarUmProdutoDTOCasoIdExista() throws Exception {

        UUID id = UUID.fromString("b8804555-5a3e-4cd2-9818-2271e795d594");

        ProdutoDTO produto = new ProdutoDTO();
        produto.setNome("teste");
        produto.setPreco(new BigDecimal(3000.55));
        produto.setDescricao("produto teste");
        produto.setUrlImagem("url imagem");
        produto.setId(id);

        Mockito.when(produtoService.findById(id)).thenReturn(produto);

        ResultActions result = mockMvc.perform(get("/produto/{id}", id).accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());



    }
}
