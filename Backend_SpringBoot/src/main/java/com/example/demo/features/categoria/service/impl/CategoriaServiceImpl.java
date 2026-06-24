package com.example.demo.features.categoria.service.impl;

import com.example.demo.core.entidades.Categoria;
import com.example.demo.features.categoria.repository.CategoriaDAO;
import com.example.demo.features.categoria.service.CategoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class CategoriaServiceImpl implements CategoriaService {
   @Autowired
   private CategoriaDAO categoriaRepository;
   @Transactional(readOnly = true)
    @Override

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
       return categoriaRepository.findById(id).orElse(null);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }
}
