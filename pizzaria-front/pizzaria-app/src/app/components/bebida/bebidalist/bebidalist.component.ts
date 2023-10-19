import { Component, inject } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Bebida } from 'src/app/models/bebida';
import { BebidaService } from 'src/app/services/bebida.service';

@Component({
  selector: 'app-bebidas-list',
  templateUrl: './bebidalist.component.html',
  styleUrls: ['./bebidalist.component.scss']
})
export class BebidasListComponent {

  lista: Bebida[] = [];

  bebidaSelecionadaParaEdicao: Bebida = new Bebida();
  indiceSelecionadoParaEdicao!: number;

  modalService = inject(NgbModal);
  bebidaService = inject(BebidaService);

  constructor() {
    this.listAll();
  }

  listAll() {
    this.bebidaService.listAll().subscribe({
      next: lista => {
        this.lista = lista;
      },
      error: erro => {
        alert('Observe o erro no console!');
        console.error(erro);
      }
    });
  }

  adicionar(modal: any) {
    this.bebidaSelecionadaParaEdicao = new Bebida();
    this.modalService.open(modal, { size: 'sm' });
  }

  editar(modal: any, bebida: Bebida, indice: number) {
    this.bebidaSelecionadaParaEdicao = { ...bebida };
    this.indiceSelecionadoParaEdicao = indice;
    this.modalService.open(modal, { size: 'sm' });
  }

  addOuEditarBebida(bebida: Bebida) {
    if (bebida.id) {
      this.bebidaService.atualizarBebida(bebida.id, bebida).subscribe(() => this.listAll());
    } else {
      this.bebidaService.cadastrarBebida(bebida).subscribe(() => this.listAll());
    }
    this.modalService.dismissAll();
  }

  deletar(id: number) {
    this.bebidaService.deletarBebida(id).subscribe(() => this.listAll());
  }
}
