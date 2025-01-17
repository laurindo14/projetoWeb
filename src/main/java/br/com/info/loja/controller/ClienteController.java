package br.com.info.loja.controller;

import br.com.info.loja.entity.Cliente;
import br.com.info.loja.service.CategoriaService;
import br.com.info.loja.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cliente")
    public ResponseEntity<Cliente> salvar (@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @GetMapping("/cliente")
    public ResponseEntity<Iterable<Cliente>> listarTodos(){
        Iterable<Cliente> clientes = clienteService.listarTodos();
        clientes.forEach(cliente -> {
            System.out.println("Cliente encontrado: "+cliente.getNome());
        });
        return ResponseEntity.ok(clientes);
    }


    @GetMapping("/cliente/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        return clienteService.buscarPorId(id);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return clienteService.deletar(id);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Cliente> atualizar(
            @PathVariable Long id,
            @RequestBody Cliente cliente){
        cliente.setId(id);
        return clienteService.salvar(cliente);
    }
}
