package br.com.ecommerce.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("livro")
public class Livro extends Produto {
    private String genero;

    public Livro() {
    }

    public Livro(String nome, BigDecimal preco, BigDecimal precoFinal, String genero) {
        super(nome, preco, precoFinal);
        this.genero = genero;
    }

    @Override
    public void calcularPrecoFinal() {
        BigDecimal precoFinal = getPreco();
        setPrecoFinal(precoFinal);
    }

    @Override
    public void atualizarEspecifico(Produto dados) {
        Livro livro = (Livro) dados;
        this.genero = livro.genero;
    }
}
