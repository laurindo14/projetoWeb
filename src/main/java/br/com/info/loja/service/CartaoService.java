package br.com.info.loja.service;

import br.com.info.loja.entity.Cartao;
import br.com.info.loja.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    // Buscar cartão por ID
    public Cartao buscarPorId(Long id) {
        return cartaoRepository.findById(id).orElse(null); // Retorna null se não encontrar
    }

    // Listar todos os cartões
    public List<Cartao> listarTodos() {
        // Converte o Iterable em uma List
        Iterable<Cartao> cartoesIterable = cartaoRepository.findAll();
        return StreamSupport.stream(cartoesIterable.spliterator(), false)
                .collect(Collectors.toList()); // Converte Iterable para List
    }

    // Salvar um cartão
    public Cartao salvar(Cartao cartao) {
        return cartaoRepository.save(cartao); // Salva o cartão no banco de dados
    }

    // Atualizar um cartão por ID
    public Cartao atualizar(Long id, Cartao cartao) {
        // Verifica se o cartão existe no banco
        Optional<Cartao> cartaoExistente = cartaoRepository.findById(id);
        if (cartaoExistente.isPresent()) {
            // Atualiza os campos do cartão com as informações fornecidas
            Cartao cartaoParaAtualizar = cartaoExistente.get();
            cartaoParaAtualizar.setNome(cartao.getNome());
            cartaoParaAtualizar.setNumeroCartao(cartao.getNumeroCartao());
            cartaoParaAtualizar.setValidade(cartao.getValidade());
            cartaoParaAtualizar.setCvc(cartao.getCvc());
            // Salva as mudanças no banco
            return cartaoRepository.save(cartaoParaAtualizar);
        } else {
            throw new RuntimeException("Cartão não encontrado com o ID: " + id); // Lança exceção se o cartão não existir
        }
    }

    // Deletar um cartão por ID
    public void deletar(Long id) {
        // Verifica se o cartão existe antes de tentar deletar
        if (!cartaoRepository.existsById(id)) {
            throw new RuntimeException("Cartão não encontrado com o ID: " + id); // Lança exceção se não existir
        }
        cartaoRepository.deleteById(id); // Deleta o cartão se existir
    }
}
