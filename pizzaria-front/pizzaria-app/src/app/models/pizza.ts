import { Sabor } from "./sabor";

export class Pizza {
    id!: number;
    sabor!: Sabor[];
    tamanho!: string;
    qtdeSabor!: number;
    valorPizza!: number;

    constructor() {
        this.sabor = [];
    }
}