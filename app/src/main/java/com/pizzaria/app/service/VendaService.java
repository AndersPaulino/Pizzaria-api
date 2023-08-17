package com.pizzaria.app.service;

import com.pizzaria.app.dto.VendaDTO;
import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.entity.Funcionario;
import com.pizzaria.app.entity.Venda;
import com.pizzaria.app.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private PedidoService pedidoService;

    public VendaDTO cadastrarVenda(VendaDTO vendaDTO) {
        Venda venda = new Venda();

        Cliente cliente = vendaDTO.getCliente().toEntity();
        Funcionario funcionario = vendaDTO.getFuncionario().toEntity();
        Pedido pedido = pedidoService.buscarPedidoPorId(vendaDTO.getPedido().getId());

        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        venda.setPedido(pedido);
        venda.setEmitirNota(vendaDTO.isEmitirNota());
        venda.setEntregar(vendaDTO.isEntregar());

        BigDecimal valorVenda = pedido.getValorPedido();
        if (venda.isEntregar()) {
            valorVenda = valorVenda.add(pedido.getValorEntrega());
        }
        venda.setValorVenda(valorVenda);

        vendaRepository.save(venda);

        vendaDTO.setId(venda.getId());
        return vendaDTO;
    }

    public List<VendaDTO> buscarVendasPorEmitirNota(boolean emitirNota) {
        List<Venda> vendas = vendaRepository.findByEmitirNota(emitirNota);
        return vendas.stream()
                .map(VendaDTO::new)
                .collect(Collectors.toList());
    }

    public List<VendaDTO> buscarVendasPorEntregar(boolean entregar) {
        List<Venda> vendas = vendaRepository.findByEntregar(entregar);
        return vendas.stream()
                .map(VendaDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<Venda> buscarVendaPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public VendaDTO atualizarVenda(Long id, VendaDTO vendaDTO) {
        Optional<Venda> vendaOptional = vendaRepository.findById(id);

        if (vendaOptional.isPresent()) {
            Venda venda = vendaOptional.get();

            Cliente cliente = vendaDTO.getCliente().toEntity();
            Funcionario funcionario = vendaDTO.getFuncionario().toEntity();
            Pedido pedido = pedidoService.buscarPedidoPorId(vendaDTO.getPedido().getId());

            venda.setCliente(cliente);
            venda.setFuncionario(funcionario);
            venda.setPedido(pedido);
            venda.setEmitirNota(vendaDTO.isEmitirNota());
            venda.setEntregar(vendaDTO.isEntregar());

            BigDecimal valorVenda = pedido.getValorPedido();
            if (venda.isEntregar()) {
                valorVenda = valorVenda.add(pedido.getValorEntrega());
            }
            venda.setValorVenda(valorVenda);

            vendaRepository.save(venda);

            vendaDTO.setId(venda.getId());
            return vendaDTO;
        }

        return null;
    }

    public void deletarVenda(Long id) {
        vendaRepository.deleteById(id);
    }

}
