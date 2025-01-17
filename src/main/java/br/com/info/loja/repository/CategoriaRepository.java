package br.com.info.loja.repository;

import br.com.info.loja.entity.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria,Long> {
    @Query (nativeQuery = true, value="select categoria " +
            " where categoria.id = {idCategoria}")
    public List<Categoria> pegarSubCategorias(Long idCategoria);


    @Query("SELECT c FROM Categoria c WHERE c.categoria.id = :parentId")
    List<Categoria> findSubcategoriasByParentId(@Param("parentId") Long parentId);


}
