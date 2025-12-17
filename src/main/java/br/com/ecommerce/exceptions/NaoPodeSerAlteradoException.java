package br.com.ecommerce.exceptions;

public class NaoPodeSerAlteradoException extends RuntimeException {
    public NaoPodeSerAlteradoException() {

        super("Esse tipo de produto não pode ser alterado!");
    }
}
