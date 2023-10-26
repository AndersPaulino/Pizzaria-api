package com.pizzaria.app.controller;

import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {this.produtoService = produtoService;}

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<ProdutoDTO> produtoDTOS = produtoService.findAll();
        return ResponseEntity.ok(produtoDTOS);
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Produto produto) {
        try {
            // Verifique se o produto é nulo
            if (produto == null) {
                return ResponseEntity.badRequest().body("O objeto produto está ausente ou vazio.");
            }

            // Verifique se o valor do produto é fornecido
            if (produto.getValorProduto() == null) {
                return ResponseEntity.badRequest().body("O valor do produto não pode estar vazio.");
            }

            produtoService.cadastrar(produto);

            return ResponseEntity.ok().body("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoAtualizado = new ProdutoDTO(produtoService.atualizar(id, produtoDTO.toProduto()));

        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
