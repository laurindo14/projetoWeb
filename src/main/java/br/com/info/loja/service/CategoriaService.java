package br.com.info.loja.service;

import br.com.info.loja.entity.Categoria;
import br.com.info.loja.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todas as categorias
    public Iterable<Categoria> listarTodos() {
        return categoriaRepository.findAll();
    }

    // Salvar ou atualizar uma categoria
    public ResponseEntity<Categoria> salvar(Categoria categoria) {
        if (categoria.getCategoria() != null && categoria.getCategoria().getId() != null) {
            Optional<Categoria> categoriaPai = categoriaRepository.findById(categoria.getCategoria().getId());
            categoriaPai.ifPresent(categoria::setCategoria);
        } else {
            categoria.setCategoria(null);  // Categoria principal (sem pai)
        }
        return new ResponseEntity<>(categoriaRepository.save(categoria), HttpStatus.CREATED);
    }

    // Buscar categoria por ID
    public ResponseEntity<Categoria> buscarPorId(Long id) {
        return new ResponseEntity<>(categoriaRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Categoria não encontrada com o ID " + id)), HttpStatus.OK);
    }

    /**
     * Método para excluir uma categoria e suas subcategorias (via cascata)
     */
    @Transactional
    public void deletar(Long id) {
        try {
            // Busca a categoria principal
            Categoria categoria = categoriaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID " + id));

            // Exclui a categoria (cascata exclui também as subcategorias)
            categoriaRepository.delete(categoria);
        } catch (Exception e) {
            // Log de erro e lançamento de exceção
            System.err.println("Erro ao excluir categoria: " + e.getMessage());
            throw new RuntimeException("Erro ao excluir a categoria. Detalhes: " + e.getMessage());
        }
    }
}
