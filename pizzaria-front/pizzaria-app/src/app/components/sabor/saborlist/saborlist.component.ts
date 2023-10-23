import { Component, inject } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Sabor } from 'src/app/models/sabor';
import { SaborService } from 'src/app/services/sabor.service';
@Component({
  selector: 'app-saborlist',
  templateUrl: './saborlist.component.html',
  styleUrls: ['./saborlist.component.scss']
})
export class SaborlistComponent {

  lista: Sabor[] = [];

  saborSelecionadaParaEdicao: Sabor = new Sabor();
  indiceSelecionadoParaEdicao!: number;

  modalService = inject(NgbModal);
  saborService = inject(SaborService);

  constructor() {
    this.listAll();
  }

  listAll() {
    this.saborService.listAll().subscribe({
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
    this.saborService.exemploErro().subscribe({
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
    this.saborSelecionadaParaEdicao = new Sabor();
    this.modalService.open(modal, { size: 'sm' });
  }

  editar(modal: any, sabor: Sabor, indice: number) {
    this.saborSelecionadaParaEdicao = { ...sabor };
    this.indiceSelecionadoParaEdicao = indice;
    this.modalService.open(modal, { size: 'sm' });
  }

  addOuEditarSabor(sabor: Sabor) {
    const onComplete = () => {
      this.listAll();
      this.modalService.dismissAll();
    };

    if (sabor.id) {
      console.log("Aqui foi atualizar");
      this.saborService.atualizarSabor(sabor.id, sabor).subscribe(onComplete);
    } else {
      console.log("Aqui foi cadastrar");
      this.saborService.cadastrarSabor(sabor).subscribe(onComplete);
    }
  }

  deletar(id: number) {
    this.saborService.deletarSabor(id).subscribe(() => this.listAll());
  }
}
