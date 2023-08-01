package br.com.fiap.pettech.dominio.categoria.dto;

import br.com.fiap.pettech.dominio.categoria.entity.Categoria;

public class CategoriaDTO {

    private Long id;
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(Categoria entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
    }
    public Long getId() {
        return id;
    }

    public CategoriaDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public CategoriaDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }
}
