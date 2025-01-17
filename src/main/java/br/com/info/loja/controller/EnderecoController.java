package br.com.info.loja.controller;

import br.com.info.loja.entity.Endereco;
import br.com.info.loja.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
        return enderecoService.salvar(endereco);
    }

    @GetMapping
    public ResponseEntity<Iterable<Endereco>> listarTodos() {
        Iterable<Endereco> enderecos = enderecoService.listarTodos();
        enderecos.forEach(endereco -> {
            System.out.println("Endereço encontrado: " + endereco.getBairro());
        });
        return ResponseEntity.ok(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
        return enderecoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return enderecoService.deletar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> atualizar(
            @PathVariable Long id,
            @RequestBody Endereco endereco) {
        endereco.setId(id);
        return enderecoService.salvar(endereco);
    }
}
