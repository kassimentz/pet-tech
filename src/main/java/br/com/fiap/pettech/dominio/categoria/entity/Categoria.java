package br.com.fiap.pettech.dominio.categoria.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataDeCriacao;

    public Categoria() {
    }

    public Categoria(Long id, String nome, Instant dataDeCriacao) {
        this.id = id;
        this.nome = nome;
        this.dataDeCriacao = dataDeCriacao;
    }

    public Long getId() {
        return id;
    }

    public Categoria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Categoria setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }

    public Categoria setDataDeCriacao(Instant dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
        return this;
    }

    @PrePersist
    public void prePersist() {
        dataDeCriacao = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        return id.equals(categoria.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
