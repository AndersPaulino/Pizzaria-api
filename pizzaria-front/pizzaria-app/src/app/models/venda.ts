import { Cliente } from "./cliente";
import { Funcionario } from "./funcionario";
import { Produto } from "./produto";

export class Venda {
    id!: number;
    cliente!: Cliente[];
    funcionario!: Funcionario[];
    produto!: Produto[];
    emitirNota!: boolean;
    entregar!: boolean;
    valorVenda!: number;

    constructor() {
        this.cliente = [];
        this.funcionario = [];
        this.produto = [];
    }
}