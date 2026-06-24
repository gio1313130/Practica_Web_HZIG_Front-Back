import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Categoria } from '../../../../model/categoria';
import { CategoriaService } from '../../../../services/categoria.service';

@Component({
  selector: 'app-categoria-form',
  imports: [ReactiveFormsModule],
  templateUrl: './categoria-form.html',
  styleUrl: './categoria-form.css'
})
export class CategoriaForm implements OnInit {

  formulario: FormGroup;

  esEdicion = false;
  idCategoria?: number;
  guardando = false;
  mensajeError = '';

  constructor(
    private formBuilder: FormBuilder,
    private categoriaService: CategoriaService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.formulario = this.formBuilder.group({
      nombreCategoria: [
        '',
        [
          Validators.required,
          Validators.minLength(4),
          Validators.maxLength(50)
        ]
      ],
      descripcionCategoria: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.paramMap.get('id');

    if (id !== null) {
      this.esEdicion = true;
      this.idCategoria = Number(id);
      this.cargarCategoria();
    }
  }

  cargarCategoria(): void {
    if (this.idCategoria === undefined) {
      return;
    }

    this.categoriaService.obtenerPorId(this.idCategoria).subscribe({
      next: (categoria) => {
        this.formulario.patchValue({
          nombreCategoria: categoria.nombreCategoria,
          descripcionCategoria: categoria.descripcionCategoria
        });
      },
      error: (error) => {
        console.error(error);
        this.mensajeError = 'No se pudo cargar la categoría.';
      }
    });
  }

  guardar(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    const categoria: Categoria = {
      nombreCategoria: this.formulario.value.nombreCategoria,
      descripcionCategoria: this.formulario.value.descripcionCategoria
    };

    this.guardando = true;
    this.mensajeError = '';

    if (this.esEdicion && this.idCategoria !== undefined) {
      this.categoriaService
        .actualizar(this.idCategoria, categoria)
        .subscribe({
          next: () => {
            this.router.navigate(['/categorias']);
          },
          error: (error) => {
            console.error(error);
            this.mensajeError = 'No se pudo actualizar la categoría.';
            this.guardando = false;
          }
        });
    } else {
      this.categoriaService.crear(categoria).subscribe({
        next: () => {
          this.router.navigate(['/categorias']);
        },
        error: (error) => {
          console.error(error);
          this.mensajeError = 'No se pudo registrar la categoría.';
          this.guardando = false;
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/categorias']);
  }
}
