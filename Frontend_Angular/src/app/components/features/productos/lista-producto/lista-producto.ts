import {
  ChangeDetectorRef,
  Component,
  OnInit
} from '@angular/core';

import { Producto } from '../../../../model/producto';
import { ProductoService } from '../../../../services/producto.service';
import { RouterLink } from '@angular/router';
@Component({
  selector: 'app-lista-producto',
  imports: [RouterLink],
  templateUrl: './lista-producto.html',
  styleUrl: './lista-producto.css'
})
export class ListaProducto implements OnInit {

  productos: Producto[] = [];
  cargando = true;
  mensajeError = '';

  constructor(
    private productoService: ProductoService,
    private changeDetectorRef: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.cargando = true;
    this.mensajeError = '';

    this.productoService.listar().subscribe({
      next: (productos) => {
        this.productos = productos.sort(
          (a, b) => (a.idProducto ?? 0) - (b.idProducto ?? 0)
        );

        this.cargando = false;
        this.changeDetectorRef.markForCheck();
      },
      error: (error) => {
        console.error(error);

        this.mensajeError =
          'No se pudieron cargar los productos.';

        this.cargando = false;
        this.changeDetectorRef.markForCheck();
      }
    });
  }

  eliminarProducto(idProducto?: number): void {
    if (idProducto === undefined) {
      return;
    }

    const confirmar =
      confirm('¿Deseas eliminar este producto?');

    if (!confirmar) {
      return;
    }

    this.productoService.eliminar(idProducto).subscribe({
      next: () => {
        this.cargarProductos();
      },
      error: (error) => {
        console.error(error);

        this.mensajeError =
          'No se pudo eliminar el producto.';

        this.changeDetectorRef.markForCheck();
      }
    });
  }
}
