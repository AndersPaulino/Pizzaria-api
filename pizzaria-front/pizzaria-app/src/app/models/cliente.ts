import { Endereco } from "./endereco";

export class Cliente {
    id!: number;
    nome!: string;
    cpf!: string;
    enderecos: Endereco[]; // Declare a propriedade, mas não a inicialize diretamente

    constructor() {
        this.enderecos = []; // Inicialize a propriedade no construtor
    }
}