package br.com.info.loja.service;

import br.com.info.loja.entity.Endereco;
import br.com.info.loja.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Iterable<Endereco> listarTodos() {
        Iterable<Endereco> enderecos = enderecoRepository.findAll();
        if (!enderecos.iterator().hasNext()) {
            System.out.println("Nenhum endereço encontrado");
        } else {
            enderecos.forEach(endereco -> System.out.println("Endereço encontrado: " + endereco.getBairro()));
        }
        return enderecos;
    }

    // Salvar ou atualizar endereço
    public ResponseEntity<Endereco> salvar(Endereco endereco) {
        // Se o cliente não for necessário, não é necessário adicionar ou verificar cliente
        Endereco enderecoSalvo = enderecoRepository.save(endereco);
        return ResponseEntity.ok(enderecoSalvo);
    }

    // Buscar endereço por ID
    public ResponseEntity<Endereco> buscarPorId(Long id) {
        return enderecoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar endereço por ID
    public ResponseEntity<Void> deletar(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
