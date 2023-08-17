package com.pizzaria.app.controller;

import com.pizzaria.app.dto.EnderecoDTO;
import com.pizzaria.app.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public EnderecoDTO criarEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        return enderecoService.criarEndereco(enderecoDTO);
    }
}
