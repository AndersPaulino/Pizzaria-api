import { Component, inject } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Endereco } from 'src/app/models/endereco';
import { EnderecoService } from 'src/app/services/endereco.service';

@Component({
  selector: 'app-enderecolist',
  templateUrl: './enderecolist.component.html',
  styleUrls: ['./enderecolist.component.scss']
})
export class EnderecolistComponent {

  lista: Endereco[] = [];

  enderecoSelecionadoParaEdicao: Endereco = new Endereco();
  indiceSelecionadoParaEdicao!: number;

  modalService = inject(NgbModal);
  enderecoService = inject(EnderecoService);

  constructor() {
    this.listAll();
  }

  listAll() {
    this.enderecoService.listAll().subscribe({
      next: lista => {
        this.lista = lista;
      },
      error: erro => {
        alert('Observe o erro no console!');
        console.error(erro);
      }
    });
  }

  exemploErro() {
    this.enderecoService.exemploErro().subscribe({
      next: lista => { // QUANDO DÁ CERTO
        this.lista = lista;
      },
      error: erro => { // QUANDO DÁ ERRO
        alert('Observe o erro no console!');
        console.error(erro);
      }
    });
  }

  adicionar(modal: any) {
    this.enderecoSelecionadoParaEdicao = new Endereco();
    this.modalService.open(modal, { size: 'sm' });
  }

  editar(modal: any, endereco: Endereco, indice: number) {
    this.enderecoSelecionadoParaEdicao = { ...endereco };
    this.indiceSelecionadoParaEdicao = indice;
    this.modalService.open(modal, { size: 'sm' });
  }

  addOuEditarEndereco(endereco: Endereco) {
    const onComplete = () => {
      this.listAll();
      this.modalService.dismissAll();
    };

    if (endereco.id) {
      console.log("Aqui foi atualizar");
      this.enderecoService.atualizarEndereco(endereco.id, endereco).subscribe(onComplete);
    } else {
      console.log("Aqui foi cadastrar");
      this.enderecoService.cadastrarEndereco(endereco).subscribe(onComplete);
    }
  }

  deletar(id: number) {
    this.enderecoService.deletarEndereco(id).subscribe(() => this.listAll());
  }
}
