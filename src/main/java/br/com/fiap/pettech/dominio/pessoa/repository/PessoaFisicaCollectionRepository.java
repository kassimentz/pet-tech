package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.pessoa.entity.PessoaFisica;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public class PessoaFisicaCollectionRepository {

    static private Set<PessoaFisica> pessoas;

    static {
        pessoas = new LinkedHashSet<>();
        PessoaFisica p1 = new PessoaFisica();
        p1.setCpf("12332123444").setNome("Nome do teste").setNascimento(LocalDate.of(1987, 1, 8));


        PessoaFisica dep1 = new PessoaFisica();
        dep1.setCpf("11111111111").setNome("nome 2").setNascimento(LocalDate.of(2000, 4, 22));

        p1.addDependente(dep1);
    }

    public Collection<PessoaFisica> findAll() {
        return pessoas;
    }

    public Optional<PessoaFisica> findById(Long id) {
        return pessoas.stream().filter(p->p.getId().equals(id)).findFirst();
    }
}
