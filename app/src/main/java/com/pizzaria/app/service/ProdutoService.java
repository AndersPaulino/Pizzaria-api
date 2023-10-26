package com.pizzaria.app.service;


import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Bebida;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.BebidaRepository;
import com.pizzaria.app.repository.PizzaRepository;
import com.pizzaria.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final BebidaRepository bebidaRepository;

    private final PizzaRepository pizzaRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, BebidaRepository bebidaRepository, PizzaRepository pizzaRepository) {
        this.produtoRepository = produtoRepository;
        this.bebidaRepository = bebidaRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Transactional(readOnly = true)
    public Optional<ProdutoDTO> findById(Long id){
        return produtoRepository.findById(id)
                .map(ProdutoDTO::new);
    }
    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByAtivo(boolean ativo){
        List<Produto> produtos = produtoRepository.findByAivo(ativo);
        return produtos.stream()
                .map(ProdutoDTO::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByDiaRegistro(LocalDate registro){
        List<Produto> produtos = produtoRepository.findByDiaRegistro(registro);
        return produtos.stream()
                .map(ProdutoDTO::new)
                .toList();
    }
    @Transactional(readOnly = true)
    public List<ProdutoDTO> findByDiaAtualizar(LocalDate atualizar){
        List<Produto> produtos = produtoRepository.findByDiaAtualizar(atualizar);
        return produtos.stream()
                .map(ProdutoDTO::new)
                .toList();
    }
    @Transactional(rollbackFor = Exception.class)
    public void cadastrar(Produto produto) {
        produtoRepository.save(produto);
    }


   /* public ProdutoDTO atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produto.setAtivo(produtoDTO.isAtivo());
        produto.setRegistro(produtoDTO.getRegistro());
        produto.setPizzaList(produtoDTO.getPizzaList());
        produto.setBebidaList(produtoDTO.getBebidaList());
        produto.setValorProduto(produtoDTO.getValorProduto());

        produtoRepository.save(produto);

        return new ProdutoDTO(produto);
    }*/

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }
}
