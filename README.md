# API de Pizzaria

Esta API permite gerenciar informações relacionadas a uma pizzaria, como clientes, funcionários, produtos e vendas.

## Endpoints

### Clientes

- **Listar todos os clientes**

  `GET /clientes`

- **Buscar cliente pelo ID**

  `GET /clientes/{id}`

- **Buscar cliente pelo Nome**

  `GET /clientes/nome/{nome}`

- **Buscar cliente pelo CPF**

  `GET /clientes/cpf/{cpf}`

- **Cadastrar cliente**

  `POST /clientes`

  Exemplo de JSON de entrada:
  ```json
    {
        "nome": "Fulano de Tal",
        "cpf": "123.456.789-10",
        "endereco": {
            "bairro": "Jd Alguma coisa",
            "rua": "Rua de Tal",
            "numero": 50
        }
    }
  ```
- **Atualizar cliente**

`PUT /clientes/{id}`

Exemplo de JSON de entrada:
```json
{
    "nome": "Nome do Cliente",
    "telefone": "123456789",
    "cpf": "123.456.789-00",
    "enderecos": [
        {
            "id": 1
        }
    ]
}
```
- **Deletar cliente**

`DELETE /clientes/{id}`

## Funcionários

- **Listar todos os funcionários**

  `GET /funcionarios`

- **Buscar funcionário pelo ID**

  `GET /funcionarios/{id}`

- **Buscar funcionário pelo Nome**

  `GET /funcionarios/nome/{nome}`

- **Cadastrar funcionário**

  `POST /funcionarios`

  Exemplo de JSON de entrada:
  ```json
    {
        "nome": "Nome do Funcionário"
    }
  ```
- **Deletar funcionário**
`DELETE /funcionarios/{id}`

...
