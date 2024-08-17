package org.esfe.servicios.implementaciones;

import org.esfe.modelos.Autor;
import org.esfe.repositorios.IAutorRepository;
import org.esfe.servicios.interfaces.IAutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService implements IAutorService {
    @Autowired
    private IAutorRepository autorRepository;

    @Override
    public Page<Autor> buscarTodosPaginados(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    @Override
    public List<Autor> obtenerTodos() {
        return autorRepository.findAll();
    }

    @Override
    public Optional<Autor> buscarPorId(Integer id) {
        return autorRepository.findById(id);
    }

    @Override
    public Autor createOrEditOne(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public void eliminarPorId(Integer id) {
        autorRepository.deleteById(id);
    }
}
