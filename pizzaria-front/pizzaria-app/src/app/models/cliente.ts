import { Endereco } from "./endereco";

export class Cliente {
    id!: number;
    nome!: string;
    cpf!: string;
    enderecos: Endereco[];

    constructor() {
        this.enderecos = [];
    }
}