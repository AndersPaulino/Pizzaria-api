import { Endereco } from "./endereco";

export class Cliente {
    id!: number;
    nome!: string;
    cpf!: string;
    endereco!: Endereco[];

    constructor() {
        this.endereco = [];
    }
}