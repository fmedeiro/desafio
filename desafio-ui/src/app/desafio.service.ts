import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DesafioService {

  private api = 'http://localhost:4200';

  constructor(private http: HttpClient) { }

  listar(): Observable<any> {
    return this.http.get<any>(`${this.api}/api/desafio/listar`);
  }

  excluirRegistro(cliente: any): Observable<any> {
    return this.http.get<any>(`${this.api}/api/desafio/excluir/${cliente.id}`)
  }

  excluirTodosRegistros(): Observable<any> {
    return this.http.get<any>(`${this.api}/api/desafio/excluir`)
  }

  cadastrar(cliente: any): Observable<any> {
    return this.http.post<any>(`${this.api}/api/desafio/cadastrar`, cliente);
  }

  listarCliente(): Observable<any> {
    return this.http.get<any>(`${this.api}/clientes`);
  }

  listarProdutos(): Observable<any> {
    return this.http.get<any>(`${this.api}/produtos`);
  }
}
