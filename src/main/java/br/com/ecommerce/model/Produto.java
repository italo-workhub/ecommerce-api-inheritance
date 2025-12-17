package br.com.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_produto", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Eletronico.class, name = "eletronico"),
        @JsonSubTypes.Type(value = Livro.class, name = "livro")
})
public abstract class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String nome;
    @Column(precision = 10, scale = 2)
    private BigDecimal preco;
    @Column(precision = 10, scale = 2)
    private BigDecimal precoFinal;

    public Produto() {
    }

    public Produto(String nome, BigDecimal preco, BigDecimal precoFinal) {
        this.nome = nome;
        setPreco(preco);
        this.precoFinal = precoFinal;
    }

    public void setPreco(BigDecimal preco) {
        if (preco != null && preco.compareTo(BigDecimal.ZERO) > 0) {
            this.preco = preco;
        } else {
            System.out.println("Erro: O preço não pode ser negativo!");
        }
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPrecoFinal() {
        return precoFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrecoFinal(BigDecimal precoFinal) {
        this.precoFinal = precoFinal;
    }

    public void calcularPrecoFinal() {
    }

    @PrePersist
    @PreUpdate
    public void executarCalculoAntesDeSalvar() {
        this.calcularPrecoFinal();
    }

    public void atualizar(Produto dados) {
        this.setNome(dados.getNome());
        this.setPreco(dados.getPreco());
    }

    public abstract void atualizarEspecifico(Produto dados);

}
