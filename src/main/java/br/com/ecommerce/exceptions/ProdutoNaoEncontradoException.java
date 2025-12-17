package br.com.ecommerce.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("Produto não encontrado");
    }
}
