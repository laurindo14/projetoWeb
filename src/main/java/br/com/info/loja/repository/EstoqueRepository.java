package br.com.info.loja.repository;
import br.com.info.loja.entity.Estoque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends CrudRepository <Estoque,Long> {
}