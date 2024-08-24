package org.esfe.repositorios;

import org.esfe.modelos.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEditorialRepository extends JpaRepository<Editorial, Integer> {
}
