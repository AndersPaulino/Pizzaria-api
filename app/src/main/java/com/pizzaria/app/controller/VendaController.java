package com.pizzaria.app.controller;

import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.entity.Venda;
import com.pizzaria.app.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
@CrossOrigin(origins = "*")
public class VendaController {


    private final VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService){
        this.vendaService = vendaService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> findaById(@PathVariable Long id){
        VendaDTO vendaDTO = vendaService.findById(id);
        return ResponseEntity.ok(vendaDTO);
    }
    @GetMapping
    public ResponseEntity<List<VendaDTO>> findAll(){
        List<VendaDTO> vendaDTOS = vendaService.findAll();
        return ResponseEntity.ok(vendaDTOS);
    }
    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody Venda venda) {
        try {
            // Verifique se a venda possui pelo menos um cliente
            if (venda.getCliente() == null || venda.getCliente().isEmpty()) {
                return ResponseEntity.badRequest().body("A venda deve ter pelo menos um cliente.");
            }

            // Verifique se a venda possui pelo menos um produto
            if (venda.getProduto() == null || venda.getProduto().isEmpty()) {
                return ResponseEntity.badRequest().body("A venda deve ter pelo menos um produto.");
            }

            // Registre a venda no serviço de venda
            vendaService.cadastrarVenda(venda);
            return ResponseEntity.ok("Registro cadastrada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizarVenda(@PathVariable Long id, @RequestBody VendaDTO vendaDTO) {
        VendaDTO vendaAtualizada = new VendaDTO(vendaService.atualizarVenda(id, vendaDTO.toVenda()));

        return ResponseEntity.ok(vendaAtualizada);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarVenda(@PathVariable Long id){
        vendaService.deleteVenda(id);
        return ResponseEntity.ok("Venda com ID " + id + " excluída com sucesso.");
    }

    @DeleteMapping("/desativar/{id}")
    public ResponseEntity<String> desativar(@PathVariable Long id){
        try {
            vendaService.desativar(id);
            return ResponseEntity.ok().body("Registro desativado com sucesso!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desativar o registro.");

        }
    }

    @GetMapping("erro")
    private ResponseEntity<List<VendaDTO>> exemploErro(){
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
