package org.esfe.servicios.implementaciones;

import org.esfe.modelos.Libro;
import org.esfe.repositorios.ILibroRepository;
import org.esfe.servicios.interfaces.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService implements ILibroService {
    @Autowired
    private ILibroRepository libroRepository;

    @Override
    public Page<Libro> buscarTodosPaginados(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> buscarPorId(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro createOrEditOne(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void eliminarPorId(Integer id) {
        libroRepository.deleteById(id);
    }
}
