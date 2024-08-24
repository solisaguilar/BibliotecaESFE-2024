package org.esfe.servicios.interfaces;

import org.esfe.modelos.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ILibroService {
    Page<Libro> buscarTodosPaginados(Pageable pageable);

    List<Libro> obtenerTodos();

    Optional<Libro> buscarPorId(Integer id);

    Libro createOrEditOne(Libro libro);

    void eliminarPorId(Integer id);
}

