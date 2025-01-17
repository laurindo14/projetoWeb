package br.com.info.loja.service;

import br.com.info.loja.entity.FluxoFinanceiro;
import br.com.info.loja.repository.FluxoFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FluxoFinanceiroService {

    @Autowired
    private FluxoFinanceiroRepository fluxoFinanceiroRepository;

    // Salva ou atualiza fluxo financeiro
    public FluxoFinanceiro salvarFluxoFinanceiro(FluxoFinanceiro fluxoFinanceiro) {
        return fluxoFinanceiroRepository.save(fluxoFinanceiro);
    }
    public boolean deletarPorId(Long id) {
        if (fluxoFinanceiroRepository.existsById(id)) {
            fluxoFinanceiroRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // Obtém fluxo financeiro por ID
    public FluxoFinanceiro obterFluxoPorId(Long id) {
        Optional<FluxoFinanceiro> fluxo = fluxoFinanceiroRepository.findById(id);
        return fluxo.orElse(null); // Retorna o fluxo se encontrado, caso contrário, null
    }

    // Lista todos os fluxos financeiros
    public List<FluxoFinanceiro> listarTodos() {
        return fluxoFinanceiroRepository.findAll();
    }

    // Calcula o saldo acumulado
    public Double calcularSaldoAcumulado() {
        List<FluxoFinanceiro> fluxos = fluxoFinanceiroRepository.findAll();
        double saldo = 0.0;

        // Itera sobre todos os fluxos financeiros e calcula o saldo
        for (FluxoFinanceiro fluxo : fluxos) {
            if ("ENTRADA".equals(fluxo.getTipo())) {
                saldo += fluxo.getValor();
            } else if ("SAÍDA".equals(fluxo.getTipo())) {
                saldo -= fluxo.getValor();
            }
        }

        return saldo;
    }
}
