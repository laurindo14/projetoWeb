package br.com.info.loja.repository;
import br.com.info.loja.entity.Vendedor;
import br.com.info.loja.entity.Vendedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface VendedorRepository extends CrudRepository<Vendedor, Long> {
    Optional<Vendedor> findByLogin(String login);
}