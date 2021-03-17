package com.github.lf2a.repositories;

import com.github.lf2a.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>CategoriaRepository.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
