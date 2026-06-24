package com.example.demo.core.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long idProducto;
    @Column(name = "nombreProducto", length = 100, nullable = false)
    private String nombreProducto;
    @Column(name = "descripcionProducto", length = 100, nullable = false)
    private  String descripcionProducto;
    @Column(name = "precioProducto",nullable = false,precision = 10,scale = 2)
    private BigDecimal precioProducto;
    @Column(name = "existencia",nullable = false)
    private int existencia;
    @Temporal(TemporalType.DATE)
    @Column(name = "createAt", nullable = true)
    private LocalDate createAt;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria")
    private Categoria idCategoria;
    @PrePersist
    public void prePersist() {
        createAt = LocalDate.now();
    }

}

