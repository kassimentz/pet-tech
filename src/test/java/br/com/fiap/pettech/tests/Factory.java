package br.com.fiap.pettech.tests;

import br.com.fiap.pettech.dominio.produto.dto.ProdutoDTO;
import br.com.fiap.pettech.dominio.produto.entity.Produto;

import java.math.BigDecimal;

public class Factory {

    public static Produto createProduto() {
        return new Produto("Iphone", "Descricao 1", "Url 1", new BigDecimal(800.00));
    }

    public static ProdutoDTO createProdutoDTO() {
        Produto produto = createProduto();
        return new ProdutoDTO(produto);
    }


}
