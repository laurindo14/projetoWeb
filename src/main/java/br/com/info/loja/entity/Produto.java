package br.com.info.loja.entity;

import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Nome não pode ser nulo
    private String nome;

    @Column(length = 1000) // Limite de tamanho para evitar erros no banco
    private String fichaTecnica;

    @ManyToOne(optional = false) // Categoria é obrigatória
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false) // Define o nome da coluna no banco
    private Categoria categoria;

    // Getter e Setter para id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para fichaTecnica
    public String getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(String fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    // Getter e Setter para categoria
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
