package com.Tienda.Libros.controller;

import com.Tienda.Libros.model.Libro;
import com.Tienda.Libros.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/libros") // http://localhost:8080/api/libros
public class LibroController {

    @Autowired
    private LibroService libroService; // inyeccion de dependencia

    @GetMapping
    public List<Libro> obtenerTodosLosLibros() {
        return libroService.obtenerTodosLosLibros();
    }

    @PostMapping
    public Libro agregarLibro(@RequestBody Libro libro) {
        return libroService.guardarLibro(libro);
    }

    @GetMapping("/{id}")
    public Libro obtenerLibroPorId(@PathVariable Long id) {
        return libroService.obtenerLibroPorId(id);
    }

    @PutMapping("/{id}")
    public Libro actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        return libroService.actualizarLibro(id, libroActualizado);
    }

    @PatchMapping("/{id}")
    public Libro aplicarCambiosLibro(@PathVariable Long id, @RequestBody Map<String, Object> cambios) {
        return libroService.aplicarCambiosLibro(id, cambios);
    }

    @DeleteMapping("/{id}")
    public void eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
    }
}
