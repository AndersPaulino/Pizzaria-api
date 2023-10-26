import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Produto } from 'src/app/models/produto';
import { Bebida } from 'src/app/models/bebida';
import { Pizza } from 'src/app/models/pizza';
import { ProdutoService } from 'src/app/services/produto.service';
@Component({
  selector: 'app-produtodetails',
  templateUrl: './produtodetails.component.html',
  styleUrls: ['./produtodetails.component.scss']
})
export class ProdutodetailsComponent {
  @Input() produto: Produto = new Produto();
  @Output() retorno = new EventEmitter<Produto>();
  modalService = inject(NgbModal);
  modalRef!: NgbModalRef;
  produtoService = inject(ProdutoService);

  constructor() { }

  salvar() {
    this.retorno.emit(this.produto);
  }

  excluirPizza(pizza: Pizza, indice: number) {
    this.produto.pizzaList.splice(indice,1);
  }

  excluirBebida(bebida: Bebida, indice: number) {
    this.produto.bebidaList.splice(indice,1);
  }

  retornoPizzaList(pizza: Pizza): void {
    this.produto.pizzaList.push(pizza);
    this.modalRef.dismiss();
  }

  retornoBebidaList(bebida: Bebida): void {
    this.produto.bebidaList.push(bebida);
    this.modalRef.dismiss();
  }

  lancar(modal: any) {
    this.modalRef = this.modalService.open(modal, { size: 'lg' });
  }
}