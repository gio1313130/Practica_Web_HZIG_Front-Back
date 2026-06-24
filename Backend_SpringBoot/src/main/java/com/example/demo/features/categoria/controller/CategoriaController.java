package com.example.demo.features.categoria.controller;

import com.example.demo.core.entidades.Categoria;
import com.example.demo.features.categoria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class    CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categoria")
    public List<Categoria> findAll(){
        return categoriaService.findAll();
    }


    @GetMapping("/categoria/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Categoria findById(@PathVariable Long id){
        return categoriaService.findById(id);
    }

    @PostMapping("/categoria")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria create(@RequestBody Categoria categoria){
        return categoriaService.save(categoria);
    }
    @PutMapping("/categoria/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria update(
            @RequestBody Categoria categoria,
            @PathVariable Long id) {
        Categoria c = categoriaService.findById(id);
        c.setNombreCategoria(categoria.getNombreCategoria());
        c.setDescripcionCategoria(categoria.getDescripcionCategoria());

        return categoriaService.save(c);
    }

    @DeleteMapping("/categoria/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        categoriaService.deleteById(id);
    }
}
