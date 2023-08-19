package com.pizzaria.app.controller;

import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.ProdutoRepository;
import com.pizzaria.app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoRepository produtorepository;

    @Autowired
    public ProdutoController(ProdutoService produtoService, ProdutoRepository produtorepository){
        this.produtoService = produtoService;
        this.produtorepository = produtorepository;
    }

    @GetMapping("/{id}")
    public Optional<Produto> findById(@PathVariable Long id){
        return  produtorepository.findById(id);
    }

    @GetMapping
    public List<Produto> findAll() {
        return produtorepository.findAll();
    }

    @PostMapping
    public ProdutoDTO cadastrarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.cadastrarProduto(produtoDTO);
    }

    @PutMapping("/{id}")
    public ProdutoDTO atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        return produtoService.atualizarProduto(id, produtoDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
