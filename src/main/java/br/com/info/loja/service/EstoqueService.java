package br.com.info.loja.service;

import br.com.info.loja.entity.Categoria;
import br.com.info.loja.entity.Estoque;
//import br.com.qualquercoisa.ecommerce.repository.EstoqueRepository;
import br.com.info.loja.repository.CategoriaRepository;
import br.com.info.loja.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service


public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Iterable<Estoque> listarTodos (){
        return estoqueRepository.findAll();
    }

    public ResponseEntity<Estoque> salvar (Estoque estoque){
        return new ResponseEntity<Estoque>(estoqueRepository.save(estoque), HttpStatus.OK);
    }

    public ResponseEntity<Estoque> buscarPorId(Long id) {
        return new ResponseEntity<Estoque>(estoqueRepository.findById(id).orElseThrow(),HttpStatus.OK);
    }

    public ResponseEntity deletar(Long id) {
        estoqueRepository.deleteById(id);
        return new ResponseEntity("{\"mensagem\":\"Removido com sucesso\"}",HttpStatus.OK);
    }



}
