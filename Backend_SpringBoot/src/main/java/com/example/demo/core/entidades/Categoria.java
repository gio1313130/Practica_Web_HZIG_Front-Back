package com.example.demo.core.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Categoria")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idCategoria;
    @Size(min = 4, max = 50, message = "debe de estar entre 4 y 50")
    @Column(length = 50,nullable = false)
    private String nombreCategoria;
    @Column(length = 100,nullable = false)
    private String descripcionCategoria;
    @Column(name = "create_at", nullable = true)
    private LocalDate createAt;
    @JsonIgnore
    @OneToMany(mappedBy = "idCategoria", cascade = CascadeType.ALL)
    private Set<Producto> productos = new HashSet<>();
    @PrePersist
    public void prePersist() {
        createAt = LocalDate.now();
    }
}
