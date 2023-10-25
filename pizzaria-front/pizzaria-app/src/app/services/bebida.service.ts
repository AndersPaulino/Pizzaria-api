import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Bebida } from '../models/bebida';

@Injectable({
    providedIn: 'root'
  })

export class BebidaService {
    API: string = 'http://localhost:8080/api/bebida';
    http = inject(HttpClient);

      listAll(): Observable<Bebida[]> {
        return this.http.get<Bebida[]>(this.API);
      }

      cadastrarBebida(bebida: Bebida): Observable<string> {
        return this.http.post(this.API, bebida, { responseType: 'text' });
      }

      atualizarBebida(id: number, bebida: Bebida): Observable<string> {
        return this.http.put(`${this.API}/${id}`, bebida, { responseType: 'text' });
      }

      deletarBebida(id: number): Observable<string> {
        return this.http.delete(`${this.API}/deletar/${id}`,{ responseType: 'text' });
      }

      exemploErro(): Observable<Bebida[]> {
        return this.http.get<Bebida[]>(this.API + '/erro');
      }
}