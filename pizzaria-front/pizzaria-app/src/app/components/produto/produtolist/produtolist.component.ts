import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Produto } from 'src/app/models/produto';
import { ProdutoService } from 'src/app/services/produto.service';
@Component({
  selector: 'app-produtolist',
  templateUrl: './produtolist.component.html',
  styleUrls: ['./produtolist.component.scss']
})
export class ProdutolistComponent {
  lista: Produto[] = [];
  produtoSelecionadoParaEdicao: Produto = new Produto();
  indiceSelecionadoParaEdicao!: number;

  @Output() retorno = new EventEmitter<Produto>();
  @Input() modoLancamento: boolean = false;

  modalService = inject(NgbModal);
  modalRef!: NgbModalRef;

  produtoService = inject(ProdutoService);

  constructor() {
    this.listAll();
  }

  listAll() {
    this.produtoService.listAll().subscribe({
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
    this.produtoService.exemploErro().subscribe({
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
    this.produtoSelecionadoParaEdicao = new Produto();
    this.modalRef = this.modalService.open(modal, { size: 'sm' });
  }

  editar(modal: any, produto: Produto, indice: number) {
    this.produtoSelecionadoParaEdicao = { ...produto };
    this.indiceSelecionadoParaEdicao = indice;
    this.modalRef = this.modalService.open(modal, { size: 'sm' });
  }

  addOuEditarProduto(produto: Produto) {
    const onComplete = () => {
      this.listAll();
      this.modalRef.dismiss();
    };

    if (produto.id) {
      console.log("Aqui foi atualizar");
      this.produtoService.atualizarProduto(produto.id, produto).subscribe(onComplete);
    } else {
      console.log("Aqui foi cadastrar");
      this.produtoService.cadastrarProduto(produto).subscribe(onComplete);
    }
  }

  deletar(id: number) {
    this.produtoService.deletarProduto(id).subscribe(() => this.listAll());
  }

  lancamento(produto: Produto){
    this.retorno.emit(produto);
  }
}
