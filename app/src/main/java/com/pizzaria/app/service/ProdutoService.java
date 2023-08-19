package com.pizzaria.app.service;

import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    public Optional<Produto> buscarProdutoPorId(Long id){
        return produtoRepository.findById(id);
    }
    public ProdutoDTO cadastrarProduto(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setAtivo(produtoDTO.isAtivo());
        produto.setRegistro(produtoDTO.getRegistro());
        produto.setPizzaList(produtoDTO.getPizzaList());
        produto.setBebidaList(produtoDTO.getBebidaList());
        produto.setValorProduto(produtoDTO.getValorProduto());

        produtoRepository.save(produto);

        return new ProdutoDTO(produto);
    }

    public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produto.setAtivo(produtoDTO.isAtivo());
        produto.setRegistro(produtoDTO.getRegistro());
        produto.setPizzaList(produtoDTO.getPizzaList());
        produto.setBebidaList(produtoDTO.getBebidaList());
        produto.setValorProduto(produtoDTO.getValorProduto());

        produtoRepository.save(produto);

        return new ProdutoDTO(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }
}
