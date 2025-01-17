package br.com.info.loja.service;

import br.com.info.loja.entity.Vendedor;
import br.com.info.loja.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository; 

    public ResponseEntity<Vendedor> salvar(Vendedor vendedor) {
        return new ResponseEntity<>(vendedorRepository.save(vendedor), HttpStatus.OK);
    }

    public Iterable<Vendedor> listarTodos() {
        return vendedorRepository.findAll();
    }

    public ResponseEntity<Vendedor> buscarPorId(Long id) {
        return new ResponseEntity<>(vendedorRepository.findById(id).orElseThrow(), HttpStatus.OK);
    }

    public ResponseEntity<String> deletar(Long id) {
        vendedorRepository.deleteById(id);
        return new ResponseEntity<>("{\"mensagem\":\"Removido com sucesso\"}", HttpStatus.OK);
    }
}
