package com.example.demo.features.producto.service.impl;

import com.example.demo.core.entidades.Producto;
import com.example.demo.features.producto.repository.ProductoRepository;
import com.example.demo.features.producto.service.ProductoService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Producto> findAll() { return productoRepository.findAll(); }

    @Override

    public Producto findById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
