package br.com.fiap.pettech.dominio.pessoa.repository;

import br.com.fiap.pettech.dominio.pessoa.entity.Pessoa;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PessoaRepository implements IPessoaRepository {

     private final JdbcTemplate jdbcTemplate;

     @Autowired
     public PessoaRepository(DataSource dataSource) {
          this.jdbcTemplate = new JdbcTemplate(dataSource);
     }

    @Override
    public List<Pessoa> findAll(int page, int pageSize) {
        int offSet = (page -1) * pageSize;
        String SQL = "SELECT * FROM pessoas LIMIT ? OFFSET ?";
        return jdbcTemplate.query(
                SQL,
                new Object[]{pageSize, offSet},
                new PessoaRowMapper()
        );

    }

    @Override
    public Pessoa findById(Long id) {
         String SQL = "SELECT * FROM pessoas WHERE ID = ?";
         List<Pessoa> pessoas = jdbcTemplate.query(
                 SQL,
                 new Object[]{id},
                 new PessoaRowMapper()
         );
        return pessoas.isEmpty() ? null : pessoas.get(0);
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
         try {
             String sql = "INSERT INTO pessoas (cpf, nome, nascimento, email) VALUES (?, ?, ?, ?)";
             this.jdbcTemplate.update(
                     sql,
                     pessoa.getCpf(),
                     pessoa.getNome(),
                     Date.valueOf(pessoa.getNascimento()),
                     pessoa.getEmail()
             );
            return pessoa;
         } catch (Exception e) {
            throw new RuntimeException(e);
         }
    }

    @Override
    public Pessoa update(Long id, Pessoa pessoa) {
        try {
            String sql = "UPDATE pessoas SET cpf = ?, nome = ?, nascimento = ?, email = ? WHERE id = ?";
            int rowsAffected = jdbcTemplate.update(
                    sql,
                    pessoa.getCpf(),
                    pessoa.getNome(),
                    Date.valueOf(pessoa.getNascimento()),
                    pessoa.getEmail(),
                    id
            );
            if(rowsAffected == 0) {
                throw new ControllerNotFoundException("Pessoa com o ID "+ id + " nao encontrado");
            } else {
                return pessoa;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        String SQL = "DELETE FROM pessoas WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(SQL, id);
        if(rowsAffected == 0) {
            throw new ControllerNotFoundException("Pessoa com o ID "+ id + " nao encontrado");
        }
    }

    private static class PessoaRowMapper implements RowMapper<Pessoa> {
        @Override
        public Pessoa mapRow(ResultSet rs, int rowNum) throws SQLException {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(rs.getLong("id"));
            pessoa.setCpf(rs.getString("cpf"));
            pessoa.setNome(rs.getString("nome"));
            pessoa.setEmail(rs.getString("email"));
            pessoa.setNascimento(rs.getDate("nascimento").toLocalDate());
            return pessoa;
        }
    }
}
