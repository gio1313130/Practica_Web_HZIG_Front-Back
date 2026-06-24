package com.example.demo.core.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Entity
    @Table(name = "Archivo")

    public class Archivo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idArchivo;
        private String nombreArchivo;
        private String tipoArchivo;
        @Basic(fetch = FetchType.LAZY)
        @Column(name = "archivo", columnDefinition = "BYTEA")
        private byte[] datosArchivo;

    }
