import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Venda } from 'src/app/models/venda';
import { Cliente } from 'src/app/models/cliente';
import { Funcionario } from 'src/app/models/funcionario';
import { Produto } from 'src/app/models/produto';
import { VendaService } from 'src/app/services/venda.service';
@Component({
  selector: 'app-vendadetails',
  templateUrl: './vendadetails.component.html',
  styleUrls: ['./vendadetails.component.scss']
})
export class VendadetailsComponent {
  @Input() venda: Venda = new Venda();
  @Output() retorno = new EventEmitter<Venda>();
  modalService = inject(NgbModal);
  modalRef!: NgbModalRef;
  vendaService = inject(VendaService);

  constructor(){}

  salvar() {
    this.retorno.emit(this.venda);
  }

  excluirCliente(cliente: Cliente, indice: number) {
    this.venda.cliente.splice(indice,1);
  }

  excluirFuncionario(funcionario: Funcionario, indice: number) {
    this.venda.funcionario.splice(indice,1);
  }

  excluirProduto(produto: Produto, indice: number) {
    this.venda.produto.splice(indice,1);
  }

  retornoClienteList(cliente: Cliente): void {
    this.venda.cliente.push(cliente);
    this.modalRef.dismiss();
  }

  retornoFuncionarioList(funcionario: Funcionario): void {
    this.venda.funcionario.push(funcionario);
    this.modalRef.dismiss();
  }

  retornoProdutoList(produto: Produto): void {
    this.venda.produto.push(produto);
    this.modalRef.dismiss();
  }

  lancar(modal: any) {
    this.modalRef = this.modalService.open(modal, { size: 'lg' });
  }
}
