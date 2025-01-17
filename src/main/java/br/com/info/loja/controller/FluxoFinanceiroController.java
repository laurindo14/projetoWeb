package br.com.info.loja.controller;

import br.com.info.loja.entity.FluxoFinanceiro;
import br.com.info.loja.service.FluxoFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fluxoFinanceiro")
public class FluxoFinanceiroController {

    @Autowired
    private FluxoFinanceiroService fluxoFinanceiroService;

    // Salvar fluxo financeiro
    @PostMapping
    public ResponseEntity<FluxoFinanceiro> salvarFluxo(@RequestBody FluxoFinanceiro fluxoFinanceiro) {
        FluxoFinanceiro fluxoSalvo = fluxoFinanceiroService.salvarFluxoFinanceiro(fluxoFinanceiro);
        return ResponseEntity.status(201).body(fluxoSalvo);
    }

    // Obter fluxo financeiro por ID com customização do JSON
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obterFluxo(@PathVariable Long id) {
        FluxoFinanceiro fluxo = fluxoFinanceiroService.obterFluxoPorId(id);

        if (fluxo == null) {
            return ResponseEntity.notFound().build();
        }

        // Construção do JSON customizado
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("id", fluxo.getId());
        resposta.put("tipo", fluxo.getTipo());
        resposta.put("valor", fluxo.getValor());
        resposta.put("data", fluxo.getData());
        resposta.put("saldo", fluxo.getSaldo());

        // Dados fictícios ou calculados para o campo "venda"
        Map<String, Object> venda = new HashMap<>();
        venda.put("descricao", "Venda de produto A"); // Exemplo estático
        venda.put("quantidade", 2);                  // Exemplo estático
        resposta.put("venda", venda);

        return ResponseEntity.ok(resposta);
    }

    // Listar todos os fluxos financeiros
    @GetMapping
    public ResponseEntity<List<FluxoFinanceiro>> listarFluxos() {
        List<FluxoFinanceiro> fluxos = fluxoFinanceiroService.listarTodos();
        return ResponseEntity.ok(fluxos);
    }

    // Calcular saldo acumulado
    @GetMapping("/saldo")
    public ResponseEntity<Double> obterSaldo() {
        Double saldo = fluxoFinanceiroService.calcularSaldoAcumulado();
        return ResponseEntity.ok(saldo);
    }

    // Deletar fluxo financeiro por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFluxo(@PathVariable Long id) {
        boolean deletado = fluxoFinanceiroService.deletarPorId(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // Retorna 204 - No Content
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 - Not Found
        }
    }
}
