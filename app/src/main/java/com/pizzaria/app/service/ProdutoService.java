package com.pizzaria.app.service;

import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        Produto entity = produtoRepository.findById(id).orElse(null);
        if (entity == null) {
            return null;
        }
        return new ProdutoDTO(entity);
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Produto produto){
        produtoRepository.save(produto);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoExistente.setPizzaList(produtoAtualizado.getPizzaList());
        produtoExistente.setBebidaList(produtoAtualizado.getBebidaList());
        produtoExistente.setValorProduto(produtoAtualizado.getValorProduto());

        return produtoRepository.save(produtoExistente);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }
}
