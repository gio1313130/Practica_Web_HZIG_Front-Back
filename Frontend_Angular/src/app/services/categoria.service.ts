import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Categoria } from '../model/categoria';
import { API_URL } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private url = `${API_URL}/v1/categorias/categoria`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Categoria[]> {
    return this.http.get<Categoria[]>(this.url);
  }

  obtenerPorId(id: number): Observable<Categoria> {
    return this.http.get<Categoria>(`${this.url}/${id}`);
  }

  crear(categoria: Categoria): Observable<Categoria> {
    return this.http.post<Categoria>(this.url, categoria);
  }

  actualizar(id: number, categoria: Categoria): Observable<Categoria> {
    return this.http.put<Categoria>(`${this.url}/${id}`, categoria);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
