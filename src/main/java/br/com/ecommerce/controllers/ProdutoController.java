package br.com.ecommerce.controllers;


import br.com.ecommerce.model.Produto;
import br.com.ecommerce.repositories.ProdutoRepository;
import br.com.ecommerce.services.ProdutoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(service.listarProdutos());
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto salvo = service.adicionarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable int id) {
        service.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable int id, @RequestBody Produto produto) {
        Produto atualizado = service.atualizarProduto(id, produto);
        return ResponseEntity.ok(atualizado);
    }
}

