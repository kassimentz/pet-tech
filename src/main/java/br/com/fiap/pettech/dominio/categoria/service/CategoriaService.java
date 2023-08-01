package br.com.fiap.pettech.dominio.categoria.service;

import br.com.fiap.pettech.dominio.categoria.dto.CategoriaDTO;
import br.com.fiap.pettech.dominio.categoria.entity.Categoria;
import br.com.fiap.pettech.dominio.categoria.repository.ICategoriaRepository;
import br.com.fiap.pettech.dominio.produto.service.exception.ControllerNotFoundException;
import br.com.fiap.pettech.dominio.produto.service.exception.DatabaseException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private ICategoriaRepository repository;

    public Page<CategoriaDTO> findAll(PageRequest pageRequest) {
        Page<Categoria> list = repository.findAll(pageRequest);
        return list.map(x -> new CategoriaDTO(x));
    }

    public CategoriaDTO findById(Long id) {
        Optional<Categoria> entity = repository.findById(id);
        Categoria categoria = entity.orElseThrow(() -> new ControllerNotFoundException("Categoria Não Encontrada"));
        return new CategoriaDTO(categoria);
    }

    public CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        mapperDTOToEntity(dto, entity);
        Categoria categoriaInserida = repository.save(entity);
        return new CategoriaDTO(categoriaInserida);
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {
        try {
            Categoria entity = repository.getOne(id);
            mapperDTOToEntity(dto, entity);
            entity = repository.save(entity);
            return new CategoriaDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Categoria nao encontrada "+ id);
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ControllerNotFoundException("Categoria nao encontrada "+ id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violacao de integridade da base");
        }
    }

    private void mapperDTOToEntity(CategoriaDTO dto, Categoria entity) {
        entity.setNome(dto.getNome());
    }
}
