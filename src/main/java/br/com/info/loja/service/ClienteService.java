package br.com.info.loja.service;

import br.com.info.loja.entity.Cliente;
import br.com.info.loja.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes
    public Iterable<Cliente> listarTodos() {
        return clienteRepository.findAll(); // Garante que retorna Iterable<Cliente>
    }

    // Salvar ou atualizar cliente
    public ResponseEntity<Cliente> salvar(Cliente cliente) {
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    // Buscar cliente por ID
    public ResponseEntity<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar cliente por ID
    public ResponseEntity<Void> deletar(Long id) {
        try {
            System.out.println("Recebido pedido para excluir cliente com ID: " + id);

            if (id == null || id <= 0) {
                System.out.println("ID inválido: " + id);
                return ResponseEntity.badRequest().build();
            }

            if (clienteRepository.existsById(id)) {
                clienteRepository.deleteById(id);
                System.out.println("Cliente com ID " + id + " excluído com sucesso.");
                return ResponseEntity.noContent().build();
            }

            System.out.println("Cliente com ID " + id + " não encontrado.");
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            System.err.println("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}