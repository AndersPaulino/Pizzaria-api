package com.pizzaria.app.service;


import com.pizzaria.app.dto.ClienteDTO;
import com.pizzaria.app.dto.FuncionarioDTO;
import com.pizzaria.app.dto.ProdutoDTO;
import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.entity.Venda;
import com.pizzaria.app.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private ProdutoService produtoService;

    public VendaDTO cadastrarVenda(VendaDTO vendaDTO) {
        Venda venda = new Venda();

        venda.setCliente(clienteService.buscarClientePorId(vendaDTO.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado")));


        venda.setProduto(produtoService.buscarProdutoPorId(vendaDTO.getProduto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado")));

        venda.setEmitirNota(vendaDTO.isEmitirNota());
        venda.setEntregar(vendaDTO.isEntregar());

        venda.setValorVenda(vendaDTO.getValorVenda());

        vendaRepository.save(venda);

        vendaDTO.setId(venda.getId());
        return vendaDTO;
    }


    public Optional<Venda> buscarVendaPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public List<VendaDTO> buscarVendasPorEntregar(boolean entregar) {
        List<Venda> vendas = vendaRepository.findByEntregar(entregar);
        return vendas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<VendaDTO> buscarVendasPorEmitirNota(boolean emitirNota) {
        List<Venda> vendas = vendaRepository.findByEmitirNota(emitirNota);
        return vendas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public VendaDTO atualizarVenda(Long id, VendaDTO vendaDTO) {
        Optional<Venda> vendaOptional = vendaRepository.findById(id);

        if (vendaOptional.isPresent()) {
            Venda venda = vendaOptional.get();
            venda.setCliente(clienteService.buscarClientePorId(vendaDTO.getCliente().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado")));


            venda.setProduto(produtoService.buscarProdutoPorId(vendaDTO.getProduto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado")));

            venda.setEmitirNota(vendaDTO.isEmitirNota());
            venda.setEntregar(vendaDTO.isEntregar());

            venda.setValorVenda(vendaDTO.getValorVenda());
            vendaRepository.save(venda);
            vendaDTO.setId(venda.getId());
            return vendaDTO;
        }

        return null;
    }
    public void deletarVenda(Long id) {
        vendaRepository.deleteById(id);
    }

    private VendaDTO convertToDTO(Venda venda) {
        VendaDTO vendaDTO = new VendaDTO(venda);
        vendaDTO.setId(venda.getId());
        vendaDTO.setCliente(new ClienteDTO(venda.getCliente()));
        vendaDTO.setFuncionario(new FuncionarioDTO(venda.getFuncionario()));
        vendaDTO.setProduto(new ProdutoDTO(venda.getProduto()));
        vendaDTO.setEmitirNota(venda.isEmitirNota());
        vendaDTO.setEntregar(venda.isEntregar());
        vendaDTO.setValorVenda(venda.getValorVenda());
        return vendaDTO;
    }
}

