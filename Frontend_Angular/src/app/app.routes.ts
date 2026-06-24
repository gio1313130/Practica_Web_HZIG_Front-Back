import { Routes } from '@angular/router';

import { CategoriaForm } from './components/features/categorias/categoria-form/categoria-form';
import { ListaCategoria } from './components/features/categorias/lista-categoria/lista-categoria';
import { ListaProducto } from './components/features/productos/lista-producto/lista-producto';
import { ProductoForm } from './components/features/productos/producto-form/producto-form';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'categorias',
    pathMatch: 'full'
  },
  {
    path: 'categorias/nueva',
    component: CategoriaForm
  },
  {
    path: 'categorias/editar/:id',
    component: CategoriaForm
  },
  {
    path: 'categorias',
    component: ListaCategoria
  },
  {
    path: 'productos/nuevo',
    component: ProductoForm
  },
  {
    path: 'productos/editar/:id',
    component: ProductoForm
  },
  {
    path: 'productos',
    component: ListaProducto
  },
  {
    path: '**',
    redirectTo: 'categorias'
  }
];
