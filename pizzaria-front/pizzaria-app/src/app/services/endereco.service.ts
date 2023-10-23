import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Endereco } from '../models/endereco';

@Injectable({
    providedIn: 'root'
  })

export class EnderecoService {
    API: string = 'http://localhost:8080/api/endereco';
    http = inject(HttpClient);

      listAll(): Observable<Endereco[]> {
        return this.http.get<Endereco[]>(this.API);
      }

      cadastrarEndereco(endereco: Endereco): Observable<string> {
        return this.http.post(this.API, endereco, { responseType: 'text' });
      }

      atualizarEndereco(id: number, endereco: Endereco): Observable<string> {
        return this.http.put(`${this.API}/${id}`, endereco, { responseType: 'text' });
      }

      deletarEndereco(id: number): Observable<string> {
        return this.http.delete(`${this.API}/deletar/${id}`,{ responseType: 'text' });
      }

      exemploErro(): Observable<Endereco[]> {
        return this.http.get<Endereco[]>(this.API + '/erro');
      }
}