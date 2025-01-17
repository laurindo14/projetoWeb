package br.com.info.loja.service;

import br.com.info.loja.entity.Produto;
import br.com.info.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Salvar Produto
    public ResponseEntity<Produto> salvar(Produto produto) {
        try {
            Produto produtoSalvo = produtoRepository.save(produto);
            return new ResponseEntity<>(produtoSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Listar Todos os Produtos
    public Iterable<Produto> listarTodos() {
        try {
            return produtoRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar produtos", e);
        }
    }

    // Buscar Produto por ID
    public ResponseEntity<Produto> buscarPorId(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Deletar Produto
    public ResponseEntity<Map<String, String>> deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Produto não encontrado");
            return new ResponseEntity<>(resposta, HttpStatus.NOT_FOUND);
        }

        try {
            produtoRepository.deleteById(id);
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Produto removido com sucesso");
            return new ResponseEntity<>(resposta, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", "Erro ao remover o produto");
            return new ResponseEntity<>(resposta, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Atualizar Produto
    public ResponseEntity<Produto> atualizar(Long id, Produto produto) {
        if (!produtoRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            produto.setId(id);
            Produto produtoAtualizado = produtoRepository.save(produto);
            return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
