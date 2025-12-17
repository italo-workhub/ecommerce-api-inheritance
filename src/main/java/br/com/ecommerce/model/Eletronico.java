package br.com.ecommerce.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("eletronico")
public class Eletronico extends Produto {

    private double voltagem;

    public Eletronico() {
    }

    public Eletronico(String nome, BigDecimal preco, BigDecimal precoFinal, double voltagem) {
        super(nome, preco, precoFinal);
        this.voltagem = voltagem;
    }

    public double getVoltagem() {
        return voltagem;
    }

    @Override
    public void calcularPrecoFinal() {
        BigDecimal precoFinal = getPreco().multiply(BigDecimal.valueOf(1.1));
        setPrecoFinal(precoFinal);
    }

    @Override
    public void atualizarEspecifico(Produto dados) {
        Eletronico eletronico = (Eletronico) dados;
        this.voltagem = eletronico.voltagem;
    }
}
