import { Categoria } from './categoria';

export interface Producto {
  idProducto?: number;
  nombreProducto: string;
  descripcionProducto: string;
  precioProducto: number;
  existencia: number;
  createAt?: string | null;
  idCategoria: Categoria;
}
