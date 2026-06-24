import { ChangeDetectorRef, Component, OnInit } from '@angular/core';

import { Categoria } from '../../../../model/categoria';
import { CategoriaService } from '../../../../services/categoria.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-lista-categoria',
  imports: [RouterLink],
  templateUrl: './lista-categoria.html',
  styleUrl: './lista-categoria.css'
})
export class ListaCategoria implements OnInit {

  categorias: Categoria[] = [];
  cargando = true;
  mensajeError = '';

  constructor(private categoriaService: CategoriaService, private changeDetectorRef: ChangeDetectorRef) {}

  ngOnInit(): void {
    this.cargarCategorias();
  }

  cargarCategorias(): void {
  this.cargando = true;
  this.mensajeError = '';

  this.categoriaService.listar().subscribe({
    next: (categorias) => {
      this.categorias = categorias.sort(
        (a, b) => (a.idCategoria ?? 0) - (b.idCategoria ?? 0)
      );
      this.cargando = false;

      this.changeDetectorRef.markForCheck();
    },
    error: (error) => {
      console.error(error);

      this.mensajeError =
        'No se pudieron cargar las categorías.';

      this.cargando = false;

      this.changeDetectorRef.markForCheck();
    }
  });
}

  eliminarCategoria(idCategoria?: number): void {
    if (idCategoria === undefined) {
      return;
    }

    const confirmar = confirm('¿Deseas eliminar esta categoría?');

    if (!confirmar) {
      return;
    }

    this.categoriaService.eliminar(idCategoria).subscribe({
      next: () => {
        this.cargarCategorias();
      },
      error: (error) => {
  console.error(error);

  this.mensajeError =
    'No se pudo eliminar la categoría.';

  this.changeDetectorRef.markForCheck();
}
    });
  }
}
