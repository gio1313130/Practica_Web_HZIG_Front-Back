package com.example.demo;

import com.example.demo.core.entidades.Categoria;
import com.example.demo.features.categoria.repository.CategoriaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MiniInventario4Bm2Application implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MiniInventario4Bm2Application.class, args);
    }
    @Autowired
    private CategoriaDAO dao;

    @Override
    public void run(String... args) throws Exception {

    }
}
