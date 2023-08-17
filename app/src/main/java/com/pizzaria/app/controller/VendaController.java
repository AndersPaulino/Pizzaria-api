package com.pizzaria.app.controller;

import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaDTO> cadastrarVenda(@RequestBody VendaDTO vendaDTO) {
        VendaDTO novaVendaDTO = vendaService.cadastrarVenda(vendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVendaDTO);
    }

    @GetMapping("/por-emitir-nota")
    public ResponseEntity<List<VendaDTO>> buscarVendasPorEmitirNota(@RequestParam boolean emitirNota) {
        List<VendaDTO> vendasDTO = vendaService.buscarVendasPorEmitirNota(emitirNota);
        return ResponseEntity.ok(vendasDTO);
    }

    @GetMapping("/por-entregar")
    public ResponseEntity<List<VendaDTO>> buscarVendasPorEntregar(@RequestParam boolean entregar) {
        List<VendaDTO> vendasDTO = vendaService.buscarVendasPorEntregar(entregar);
        return ResponseEntity.ok(vendasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarVendaPorId(@PathVariable Long id) {
        Optional<VendaDTO> vendaDTO = vendaService.buscarVendaPorId(id)
                .map(VendaDTO::new);

        return vendaDTO.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizarVenda(@PathVariable Long id, @RequestBody VendaDTO vendaDTO) {
        VendaDTO vendaAtualizadaDTO = vendaService.atualizarVenda(id, vendaDTO);

        if (vendaAtualizadaDTO != null) {
            return ResponseEntity.ok(vendaAtualizadaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }

}
