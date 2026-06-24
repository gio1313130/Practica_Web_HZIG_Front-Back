import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Producto } from '../model/producto';
import { API_URL } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private url = `${API_URL}/v1/productos`;

  constructor(private http: HttpClient) {}

  listar(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.url);
  }

  obtenerPorId(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.url}/${id}`);
  }

  crear(producto: Producto): Observable<Producto> {
    return this.http.post<Producto>(this.url, producto);
  }

  actualizar(id: number, producto: Producto): Observable<Producto> {
    return this.http.put<Producto>(`${this.url}/${id}`, producto);
  }

  eliminar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
