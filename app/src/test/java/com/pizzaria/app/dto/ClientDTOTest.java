package com.pizzaria.app.dto;

import com.pizzaria.app.entity.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ClientDTOTest {

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @Test
    public void testDefaultConstructor() {
        clienteDTO = new ClienteDTO();

        assertThat(clienteDTO).isNotNull();
        assertThat(clienteDTO.getId()).isNull();
        assertThat(clienteDTO.getNome()).isNull();
        assertThat(clienteDTO.getCpf()).isNull();
        assertThat(clienteDTO.getEndereco()).isNull();
    }

   /* @Test
    public void testConstructorWithClienteObject() {
        cliente.setId(1L);
        cliente.setNome("John Doe");
        cliente.setCpf("12345678900");

        clienteDTO = new ClienteDTO(cliente);

        assertThat(clienteDTO).isNotNull();
        assertThat(clienteDTO.getId()).isEqualTo(cliente.getId());
        assertThat(clienteDTO.getNome()).isEqualTo(cliente.getNome());
        assertThat(clienteDTO.getCpf()).isEqualTo(cliente.getCpf());
        assertThat(clienteDTO.getEndereco()).isNull(); // Assuming there is no address in ClienteDTO constructor
    }*/

    @Test
    public void testGetterAndSetterMethods() {
        clienteDTO = new ClienteDTO();

        clienteDTO.setId(1L);
        clienteDTO.setNome("Jane Doe");
        clienteDTO.setCpf("98765432100");

        assertThat(clienteDTO.getId()).isEqualTo(1L);
        assertThat(clienteDTO.getNome()).isEqualTo("Jane Doe");
        assertThat(clienteDTO.getCpf()).isEqualTo("98765432100");
        assertThat(clienteDTO.getEndereco()).isNull();
    }
}
