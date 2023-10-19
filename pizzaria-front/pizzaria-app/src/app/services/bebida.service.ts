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

      cadastrarBebida(bebida: Bebida): Observable<Bebida> {
        return this.http.post<Bebida>(this.API, bebida);
      }

      atualizarBebida(id: number, bebida: Bebida): Observable<Bebida> {
        return this.http.put<Bebida>(`${this.API}/${id}`, bebida);
      }
    
      deletarBebida(id: number): Observable<void> {
        return this.http.delete<void>(`${this.API}/${id}`);
      }
}