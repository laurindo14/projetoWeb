package br.com.info.loja.repository;

import br.com.info.loja.entity.ProdutoEstoque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoEstoqueRepository extends CrudRepository<ProdutoEstoque, Long> {
}