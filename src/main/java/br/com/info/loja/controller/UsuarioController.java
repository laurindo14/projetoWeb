
package br.com.info.loja.controller;

import br.com.info.loja.entity.Categoria;
import br.com.info.loja.entity.Usuario;
import br.com.info.loja.repository.UsuarioRepository;
import br.com.info.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {


    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario){
        return usuarioService.salvar(usuario);
    }
    @GetMapping("/usuario")
    public Iterable<Usuario> listarTodos (){
        return usuarioService.listarTodos();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
        return usuarioService.buscarPorId(id);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        return usuarioService.deletar(id);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario){
        usuario.setId(id);
        return usuarioService.salvar(usuario);
    }
}
