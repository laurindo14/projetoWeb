package br.com.info.loja.repository;

import br.com.info.loja.entity.FluxoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FluxoFinanceiroRepository extends JpaRepository<FluxoFinanceiro, Long> {
}
