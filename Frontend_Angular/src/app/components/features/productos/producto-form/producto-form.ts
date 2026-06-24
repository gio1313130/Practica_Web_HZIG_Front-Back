import {
  ChangeDetectorRef,
  Component,
  OnInit
} from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { ActivatedRoute, Router } from '@angular/router';

import { Categoria } from '../../../../model/categoria';
import { Producto } from '../../../../model/producto';
import { CategoriaService } from '../../../../services/categoria.service';
import { ProductoService } from '../../../../services/producto.service';
@Component({
  selector: 'app-producto-form',
  imports: [ReactiveFormsModule],
  templateUrl: './producto-form.html',
  styleUrl: './producto-form.css'
})
export class ProductoForm implements OnInit {

  formulario: FormGroup;

  categorias: Categoria[] = [];

  esEdicion = false;
  idProducto?: number;

  cargandoCategorias = true;
  guardando = false;
  mensajeError = '';

  constructor(
    private formBuilder: FormBuilder,
    private productoService: ProductoService,
    private categoriaService: CategoriaService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private changeDetectorRef: ChangeDetectorRef
  ) {
    this.formulario = this.formBuilder.group({
      nombreProducto: ['', Validators.required],
      descripcionProducto: ['', Validators.required],
      precioProducto: [
        null,
        [
          Validators.required,
          Validators.min(0)
        ]
      ],
      existencia: [
        null,
        [
          Validators.required,
          Validators.min(0)
        ]
      ],
      idCategoria: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.cargarCategorias();

    const id = this.activatedRoute.snapshot.paramMap.get('id');

    if (id !== null) {
      this.esEdicion = true;
      this.idProducto = Number(id);
      this.cargarProducto();
    }
  }

  cargarCategorias(): void {
    this.categoriaService.listar().subscribe({
      next: (categorias) => {
        this.categorias = categorias.sort(
          (a, b) => (a.idCategoria ?? 0) - (b.idCategoria ?? 0)
        );

        this.cargandoCategorias = false;
        this.changeDetectorRef.markForCheck();
      },
      error: (error) => {
        console.error(error);

        this.mensajeError =
          'No se pudieron cargar las categorías.';

        this.cargandoCategorias = false;
        this.changeDetectorRef.markForCheck();
      }
    });
  }

  cargarProducto(): void {
    if (this.idProducto === undefined) {
      return;
    }

    this.productoService.obtenerPorId(this.idProducto).subscribe({
      next: (producto) => {
        this.formulario.patchValue({
          nombreProducto: producto.nombreProducto,
          descripcionProducto: producto.descripcionProducto,
          precioProducto: producto.precioProducto,
          existencia: producto.existencia,
          idCategoria: producto.idCategoria?.idCategoria
        });

        this.changeDetectorRef.markForCheck();
      },
      error: (error) => {
        console.error(error);

        this.mensajeError =
          'No se pudo cargar el producto.';

        this.changeDetectorRef.markForCheck();
      }
    });
  }

  guardar(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    const idCategoria =
      Number(this.formulario.value.idCategoria);

    const categoriaSeleccionada =
      this.categorias.find(
        categoria =>
          categoria.idCategoria === idCategoria
      );

    if (!categoriaSeleccionada) {
      this.mensajeError =
        'Debes seleccionar una categoría válida.';

      this.changeDetectorRef.markForCheck();
      return;
    }

    const producto: Producto = {
      nombreProducto:
        this.formulario.value.nombreProducto,

      descripcionProducto:
        this.formulario.value.descripcionProducto,

      precioProducto:
        Number(this.formulario.value.precioProducto),

      existencia:
        Number(this.formulario.value.existencia),

      idCategoria: categoriaSeleccionada
    };

    this.guardando = true;
    this.mensajeError = '';

    if (
      this.esEdicion &&
      this.idProducto !== undefined
    ) {
      this.productoService
        .actualizar(this.idProducto, producto)
        .subscribe({
          next: () => {
            this.router.navigate(['/productos']);
          },
          error: (error) => {
            console.error(error);

            this.mensajeError =
              'No se pudo actualizar el producto.';

            this.guardando = false;
            this.changeDetectorRef.markForCheck();
          }
        });

    } else {
      this.productoService.crear(producto).subscribe({
        next: () => {
          this.router.navigate(['/productos']);
        },
        error: (error) => {
          console.error(error);

          this.mensajeError =
            'No se pudo registrar el producto.';

          this.guardando = false;
          this.changeDetectorRef.markForCheck();
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigate(['/productos']);
  }
}
