package org.esfe.servicios.implementaciones;

import org.esfe.modelos.Editorial;
import org.esfe.repositorios.IEditorialRepository;
import org.esfe.servicios.interfaces.IEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService implements IEditorialService {
    @Autowired
    private IEditorialRepository editorialRepository;

    @Override
    public Page<Editorial> buscarTodosPaginados(Pageable pageable) {
        return editorialRepository.findAll(pageable);
    }

    @Override
    public List<Editorial> obtenerTodos() {
        return editorialRepository.findAll();
    }

    @Override
    public Optional<Editorial> buscarPorId(Integer id) {
        return editorialRepository.findById(id);
    }

    @Override
    public Editorial createOrEditOne(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public void eliminarPorId(Integer id) {
        editorialRepository.deleteById(id);
    }
}
