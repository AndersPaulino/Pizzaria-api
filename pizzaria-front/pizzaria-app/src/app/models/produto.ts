import { Bebida } from "./bebida";
import { Pizza } from "./pizza";

export class Produto {
    id!: number;
    pizzaList!: Pizza[];
    bebidaList!: Bebida[];
    valorProduto!: number;

    constructor() {
        this.pizzaList = [];
        this.bebidaList = [];
    }
}