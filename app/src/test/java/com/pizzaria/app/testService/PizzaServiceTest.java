package com.pizzaria.app.testService;

import com.pizzaria.app.dto.PizzaDTO;
import com.pizzaria.app.entity.Pizza;
import com.pizzaria.app.entity.Tamanho;
import com.pizzaria.app.repository.PizzaRepository;
import com.pizzaria.app.service.ClienteService;
import com.pizzaria.app.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        // Crie uma instância de Pizza para simular o retorno do repositório
        Pizza pizza = new Pizza();
        pizza.setId(1L);

        // Configure o comportamento do mock do repositório
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));

        // Chame o método findById da PizzaService
        PizzaDTO pizzaDTO = pizzaService.findById(1L);

        // Verifique se o resultado não é nulo e se o ID corresponde
        assertNotNull(pizzaDTO);
        assertEquals(1L, pizzaDTO.getId());
    }

    @Test
    public void testFindAll() {
        // Crie algumas instâncias de Pizza para simular o retorno do repositório
        Pizza pizza1 = new Pizza();
        pizza1.setId(1L);

        Pizza pizza2 = new Pizza();
        pizza2.setId(2L);

        List<Pizza> pizzas = new ArrayList<>();
        pizzas.add(pizza1);
        pizzas.add(pizza2);

        // Configura o comportamento do mock do repositório
        when(pizzaRepository.findAll()).thenReturn(pizzas);

        // Chame o método findAll da PizzaService
        List<PizzaDTO> pizzaDTOs = pizzaService.findAll();

        // Verifique se a lista de DTOs não está vazia e se contém os IDs esperados
        assertNotNull(pizzaDTOs);
        assertEquals(2, pizzaDTOs.size());
        assertEquals(1L, pizzaDTOs.get(0).getId());
        assertEquals(2L, pizzaDTOs.get(1).getId());
    }

    @Test
    public void testCadastrarPizzaComTamanhoInvalido() {
        // Crie uma instância de Pizza com tamanho inválido
        Pizza pizza = new Pizza();
        pizza.setTamanho(null);

        // Tenta cadastrar a pizza
        assertThrows(IllegalArgumentException.class, () -> pizzaService.cadastrar(pizza));
    }

    @Test
    public void testAtualizarPizza() {
        // Crie uma instância de Pizza fictícia para usar no teste
        Long pizzaId = 1L;
        Pizza pizzaExistente = new Pizza();
        pizzaExistente.setId(pizzaId);
        pizzaExistente.setTamanho(Tamanho.GRANDE);
        pizzaExistente.setValorPizza(BigDecimal.valueOf(20.0));

        // Crie uma instância de PizzaDTO com os dados atualizados
        PizzaDTO pizzaAtualizada = new PizzaDTO();
        pizzaAtualizada.setTamanho(Tamanho.MEDIA);
        pizzaAtualizada.setValorPizza(BigDecimal.valueOf(15.0));

        // Configure o comportamento simulado do repositório
        when(pizzaRepository.findById(pizzaId)).thenReturn(Optional.of(pizzaExistente));
        when(pizzaRepository.save(Mockito.any(Pizza.class))).then(AdditionalAnswers.returnsFirstArg());

        // Chame o método a ser testado
        PizzaDTO pizzaDTOAtualizada = PizzaDTO.fromPizza(pizzaService.atualizarPizza(pizzaId, pizzaAtualizada.toPizza()));

        // Verifique se o método save do repositório foi chamado com a pizza correta
        verify(pizzaRepository, times(1)).save(Mockito.any(Pizza.class));

        // Verifique se os dados da pizza atualizada correspondem ao esperado
        assertEquals(Tamanho.MEDIA, pizzaDTOAtualizada.getTamanho());
        assertEquals(BigDecimal.valueOf(15.0), pizzaDTOAtualizada.getValorPizza());
    }

    @Test
    public void testAtualizarPizzaInexistente() {
        // Crie uma instância de PizzaDTO com os dados atualizados
        PizzaDTO pizzaAtualizada = new PizzaDTO();
        pizzaAtualizada.setTamanho(Tamanho.MEDIA);
        pizzaAtualizada.setValorPizza(BigDecimal.valueOf(15.0));

        // Configure o comportamento simulado do repositório para retornar Optional vazio
        when(pizzaRepository.findById(1L)).thenReturn(Optional.empty());

        // Tente atualizar uma pizza inexistente
        assertThrows(IllegalArgumentException.class, () -> pizzaService.atualizarPizza(1L, pizzaAtualizada.toPizza()), "Atualizar uma pizza inexistente deve lançar IllegalArgumentException");
    }
    @Test
    public void testDeletarPizza() {
        // Crie um objeto Pizza fictício para usar no teste
        Long pizzaId = 1L;
        Pizza pizza = new Pizza();
        pizza.setId(pizzaId);

        // Configure o comportamento simulado do repositório
        when(pizzaRepository.findById(pizzaId)).thenReturn(Optional.of(pizza));

        // Chame o método a ser testado
        pizzaService.deletarPizza(pizzaId);

        // Verifique se o método delete do repositório foi chamado com o objeto Pizza correto
        verify(pizzaRepository, times(1)).delete(pizza);
    }
    @Test
    public void testDeletarPizzaInexistente() {
        // Configure o comportamento do mock do repositório para retornar Optional vazio
        when(pizzaRepository.findById(1L)).thenReturn(Optional.empty());

        // Tente deletar uma pizza inexistente
        assertThrows(IllegalArgumentException.class, () -> pizzaService.deletarPizza(1L), "Deletar uma pizza inexistente deve lançar IllegalArgumentException");

    }
}

