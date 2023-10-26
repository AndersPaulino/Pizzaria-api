import { Injectable, inject } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venda } from '../models/venda';

@Injectable({
  providedIn: 'root'
})

export class VendaService {
  API: string = 'http://localhost:8080/api/venda';
  http = inject(HttpClient);

  listAll(): Observable<Venda[]> {
    return this.http.get<Venda[]>(this.API);
  }

  cadastrarVenda(venda: Venda): Observable<string> {
    return this.http.post(this.API, venda, { responseType: 'text' });
  }

  atualizarVenda(id: number, venda: Venda): Observable<string> {
    return this.http.put(`${this.API}/${id}`, venda, { responseType: 'text' });
  }

  deletarVenda(id: number): Observable<string> {
    return this.http.delete(`${this.API}/deletar/${id}`, { responseType: 'text' });
  }

  exemploErro(): Observable<Venda[]> {
    return this.http.get<Venda[]>(this.API + '/erro');
  }
}