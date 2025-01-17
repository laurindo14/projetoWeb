
package br.com.info.loja.controller;

import br.com.info.loja.entity.Categoria;
import br.com.info.loja.entity.Vendedor;
import br.com.info.loja.repository.UsuarioRepository;
import br.com.info.loja.service.UsuarioService;
import br.com.info.loja.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VendedorController {


    @Autowired
    private VendedorService usuarioService;

    @PostMapping("/vendedor")
    public ResponseEntity<Vendedor> salvar (@RequestBody Vendedor vendedor){
        return usuarioService.salvar(vendedor);
    }
    @GetMapping("/vendedor")
    public Iterable<Vendedor> listarTodos (){
        return usuarioService.listarTodos();
    }

    @GetMapping("/vendedor/{id}")
    public ResponseEntity<Vendedor> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/vendedor/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return usuarioService.deletar(id);
    }

    @PutMapping("/vendedor/{id}")
    public ResponseEntity<Vendedor> atualizar(
            @PathVariable Long id,
            @RequestBody Vendedor vendedor){
        vendedor.setId(id);
        return usuarioService.salvar(vendedor);
    }
}
