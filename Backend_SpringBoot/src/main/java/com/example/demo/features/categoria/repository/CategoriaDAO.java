package com.example.demo.features.categoria.repository;

import com.example.demo.core.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface    CategoriaDAO extends JpaRepository<Categoria,Long> {

}
