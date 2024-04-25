package com.Tienda.Libros.service;

import com.Tienda.Libros.model.Libro;
import com.Tienda.Libros.repository.LibroRepository;

import io.micrometer.common.lang.NonNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository; // inyeccion de dependencia

    public List<Libro> obtenerTodosLosLibros() {
        /* System.out.println("LibroRepository: " + libroRepository); */
        return libroRepository.findAll();
    }

    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libroActualizado) {
        if (libroRepository.existsById(id)) {
            return libroRepository.save(libroActualizado);
        } else {
            return null;
        }
    }

    public Libro aplicarCambiosLibro(Long id, Map<String, Object> cambios) {
        Libro libro = libroRepository.findById(id).orElse(null);

        if (libro != null) {
            Class<?> clase = libro.getClass();

            for (Map.Entry<String, Object> entry : cambios.entrySet()) {
                String nombreCampo = entry.getKey(); // Se itera sobre el mapa de cambios, donde cada entrada representa
                                                     // un campo a actualizar y su nuevo valor.
                Object nuevoValor = entry.getValue();

                try {
                    Field campo = clase.getDeclaredField(nombreCampo); // reflexión para obtener dinámicamente el campo
                                                                       // de la clase del objeto Libro.
                    campo.setAccessible(true); // se establece el campo como accesible
                    campo.set(libro, nuevoValor); // se asigna nuevo valor al campo libro
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            libroRepository.save(libro);
        }
        return libro;
    }

    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
