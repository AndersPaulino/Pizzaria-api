import { Component, EventEmitter, Input, Output, inject } from '@angular/core';
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

  bebidaService = inject(BebidaService);

  constructor(){}

  salvar() {
    this.bebidaService.cadastrarBebida(this.bebida).subscribe({
      next: (resposta: string) => {
        console.log('Resposta do servidor:', resposta);
        if (resposta === 'Registro cadastrado com sucesso!') {
          // A resposta do servidor indica sucesso
          console.log('Bebida cadastrada com sucesso:', this.bebida);
          this.retorno.emit(this.bebida);
        } else {
          // A resposta do servidor indica um erro
          console.error('Erro ao cadastrar a bebida:', resposta);
        }
      },
      error: (erro) => {
        alert('Erro na solicitação ao servidor. Verifique o console para detalhes.');
        console.error(erro);
      }
    });
  }
  
  
}
