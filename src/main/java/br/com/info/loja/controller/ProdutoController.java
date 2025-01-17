package br.com.info.loja.controller;

import br.com.info.loja.entity.Produto;
import br.com.info.loja.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produto") // Define um prefixo comum para todas as rotas
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
        try {
            return produtoService.salvar(produto);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna erro 400 em caso de dados inválidos
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Produto>> listarTodos() {
        try {
            return new ResponseEntity<>(produtoService.listarTodos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        try {
            return produtoService.buscarPorId(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o produto não for encontrado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            return produtoService.deletar(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao tentar deletar o produto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            produto.setId(id);
            return produtoService.atualizar(id, produto); // Atualização via método específico no Service
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Retorna erro 400 em caso de falha
        }
    }
}
