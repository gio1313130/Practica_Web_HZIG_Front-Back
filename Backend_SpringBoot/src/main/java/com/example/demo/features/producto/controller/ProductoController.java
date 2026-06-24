package com.example.demo.features.producto.controller;

import com.example.demo.core.entidades.Producto;
import com.example.demo.features.mail.service.EmailService;
import com.example.demo.features.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.core.entidades.Categoria;
import com.example.demo.features.categoria.service.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/v1/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private EmailService emailService;

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Producto> findAll(){
        return  productoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Producto findById(@PathVariable Long id){
        return  productoService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto create(@RequestBody Producto producto){
        Long idCategoria = producto.getIdCategoria().getIdCategoria();

        Categoria categoria = categoriaService.findById(idCategoria);

        producto.setIdCategoria(categoria);

        Producto productoGuardado = productoService.save(producto);

    String nombreCategoria = "Sin categoría";

    if (productoGuardado.getIdCategoria() != null) {
        nombreCategoria =
                productoGuardado
                        .getIdCategoria()
                        .getNombreCategoria();
    }

    String mensaje =
            "<h2>Nuevo producto registrado</h2>" +
            "<p><strong>ID:</strong> " +
            productoGuardado.getIdProducto() + "</p>" +

            "<p><strong>Nombre:</strong> " +
            productoGuardado.getNombreProducto() + "</p>" +

            "<p><strong>Descripción:</strong> " +
            productoGuardado.getDescripcionProducto() + "</p>" +

            "<p><strong>Precio:</strong> $" +
            productoGuardado.getPrecioProducto() + "</p>" +

            "<p><strong>Existencia:</strong> " +
            productoGuardado.getExistencia() + "</p>" +

            "<p><strong>Categoría:</strong> " +
            nombreCategoria + "</p>" +

            "<p><strong>Fecha:</strong> " +
            productoGuardado.getCreateAt() + "</p>";

    emailService.enviarCorreoElectronico(
            "gio13.hedz@gmail.com",
            "Nuevo producto registrado",
            mensaje
    );

    return productoGuardado;
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id,@RequestBody Producto producto){
        Producto p = productoService.findById(id);
        p.setNombreProducto(producto.getNombreProducto());
        p.setDescripcionProducto(producto.getDescripcionProducto());
        p.setExistencia(producto.getExistencia());
        p.setPrecioProducto(producto.getPrecioProducto());
        p.setIdCategoria(producto.getIdCategoria());
        return productoService.save(p);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        productoService.deleteById(id);
    }


}


