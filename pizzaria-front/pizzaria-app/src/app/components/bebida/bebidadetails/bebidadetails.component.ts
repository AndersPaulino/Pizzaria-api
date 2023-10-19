import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Bebida } from 'src/app/models/bebida';
import { BebidaService } from 'src/app/services/bebida.service';


@Component({
  selector: 'app-bebida-details',
  templateUrl: './bebidadetails.component.html',
  styleUrls: ['./bebidadetails.component.scss']
})
export class BebidaDetailsComponent {

  @Input() bebida: Bebida = new Bebida();
  @Output() retorno = new EventEmitter<Bebida>();

  constructor(private bebidaService: BebidaService) {}

  salvar() {

    this.bebidaService.cadastrarBebida(this.bebida).subscribe({
      next: bebida => {
        this.retorno.emit(bebida);
      },
      error: erro => {
        alert('Exemplo de tratamento de erro/exception! Observe o erro no console.');
        console.error(erro);
      }
    });
  }
}
