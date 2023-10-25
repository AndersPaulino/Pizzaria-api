import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cliente } from '../models/cliente';

@Injectable({
    providedIn: 'root'
})
export class ClienteService {
    API: string = 'http://localhost:8080/api/cliente';
    http = inject(HttpClient);

    constructor() { }

    listAll(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>(this.API);
    }

    cadastrarCliente(cliente: Cliente): Observable<string> {
        return this.http.post(this.API, cliente, { responseType: 'text' });
    }

    atualizarCliente(id: number, cliente: Cliente): Observable<string> {
        return this.http.put(`${this.API}/${id}`, cliente, { responseType: 'text' });
    }

    deletarCliente(id: number): Observable<string> {
        return this.http.delete(`${this.API}/deletar/${id}`, { responseType: 'text' });
    }

    exemploErro(): Observable<Cliente[]> {
        return this.http.get<Cliente[]>(this.API + '/erro');
    }
}