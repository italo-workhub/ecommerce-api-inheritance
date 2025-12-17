package br.com.ecommerce.services;

import br.com.ecommerce.exceptions.NaoPodeSerAlteradoException;
import br.com.ecommerce.exceptions.ProdutoNaoEncontradoException;
import br.com.ecommerce.model.Produto;
import br.com.ecommerce.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProdutoService {


    private ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = repository.findAll();

        for (Produto p : produtos) {
            p.calcularPrecoFinal();
        }
        return produtos;
    }

    public Produto adicionarProduto(Produto produto) {
        return repository.save(produto);
    }

    public void deletarProduto(int id) {
        if (!repository.existsById(id)) {
            throw new ProdutoNaoEncontradoException();
        }
        repository.deleteById(id);
    }

    public Produto atualizarProduto(int id, Produto dados) {
        Produto existente = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException());

        if (!existente.getClass().equals(dados.getClass())) {
            throw new NaoPodeSerAlteradoException();
        }
        existente.atualizar(dados);
        existente.atualizarEspecifico(dados);
        return repository.save(existente);
    }
}
