package com.github.lf2a.repositories;

import com.github.lf2a.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <h1>EnderecoRepository.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
