package br.com.info.loja.controller;

import br.com.info.loja.entity.Cartao;
import br.com.info.loja.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartao") // Define o caminho base
@CrossOrigin(origins = "http://localhost:3000") // Permite requisições do frontend
public class CartaoController {

    @Autowired
    private CartaoService cartaoService;

    // GET - Listar todos os cartões
    @GetMapping
    public ResponseEntity<List<Cartao>> listarTodos() {
        List<Cartao> cartoes = cartaoService.listarTodos();
        return ResponseEntity.ok(cartoes);
    }

    // GET - Buscar cartão por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cartao> buscarPorId(@PathVariable Long id) {
        Cartao cartao = cartaoService.buscarPorId(id);
        if (cartao != null) {
            return ResponseEntity.ok(cartao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Retorna 404 se o cartão não existir
        }
    }

    // POST - Salvar um novo cartão
    @PostMapping
    public ResponseEntity<Cartao> salvar(@RequestBody Cartao cartao) {
        Cartao cartaoSalvo = cartaoService.salvar(cartao);
        return new ResponseEntity<>(cartaoSalvo, HttpStatus.CREATED);
    }

    // PUT - Atualizar cartão existente
    @PutMapping("/{id}")
    public ResponseEntity<Cartao> atualizar(@PathVariable Long id, @RequestBody Cartao cartao) {
        try {
            Cartao cartaoAtualizado = cartaoService.atualizar(id, cartao);
            return ResponseEntity.ok(cartaoAtualizado);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // Retorna 404 se o cartão não existir
        }
    }

    // DELETE - Remover cartão por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        try {
            cartaoService.deletar(id);
            return ResponseEntity.ok("{\"mensagem\": \"Cartão removido com sucesso\"}");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"erro\": \"Cartão não encontrado: " + ex.getMessage() + "\"}");
        }
    }
}
