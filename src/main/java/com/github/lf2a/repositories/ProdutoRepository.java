package com.github.lf2a.repositories;

import com.github.lf2a.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h1>ProdutoRepository.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
