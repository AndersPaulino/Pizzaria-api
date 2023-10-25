import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Funcionario } from '../models/funcionario';

@Injectable({
    providedIn: 'root'
  })

export class FuncionarioService {
    API: string = 'http://localhost:8080/api/funcionario';
    http = inject(HttpClient);

      listAll(): Observable<Funcionario[]> {
        return this.http.get<Funcionario[]>(this.API);
      }

      cadastrarFuncionario(funcionario: Funcionario): Observable<string> {
        return this.http.post(this.API, funcionario, { responseType: 'text' });
      }

      atualizarFuncionario(id: number, funcionario: Funcionario): Observable<string> {
        return this.http.put(`${this.API}/${id}`, funcionario, { responseType: 'text' });
      }

      deletarFuncionario(id: number): Observable<string> {
        return this.http.delete(`${this.API}/deletar/${id}`,{ responseType: 'text' });
      }

      exemploErro(): Observable<Funcionario[]> {
        return this.http.get<Funcionario[]>(this.API + '/erro');
      }
}