package com.Tienda.Libros.repository;

import com.Tienda.Libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    // metodos crud proporcionados por JPA
    // ej: List<Producto> findByNombre(String nombre);
    //    List<Producto> findByPrecioGreaterThan(double precio);
}
