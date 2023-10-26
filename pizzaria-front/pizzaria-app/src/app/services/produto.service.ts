import { Injectable, inject } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produto } from '../models/produto';

@Injectable({
  providedIn: 'root'
})

export class ProdutoService {
  API: string = 'http://localhost:8080/api/produto';
  http = inject(HttpClient);

  listAll(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.API);
  }

  cadastrarProduto(produto: Produto): Observable<string> {
    return this.http.post(this.API, produto, { responseType: 'text' });
  }

  atualizarProduto(id: number, produto: Produto): Observable<string> {
    return this.http.put(`${this.API}/${id}`, produto, { responseType: 'text' });
  }

  deletarProduto(id: number): Observable<string> {
    return this.http.delete(`${this.API}/deletar/${id}`, { responseType: 'text' });
  }

  exemploErro(): Observable<Produto[]> {
    return this.http.get<Produto[]>(this.API + '/erro');
  }
}