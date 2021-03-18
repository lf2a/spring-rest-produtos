package com.github.lf2a.services;

import com.github.lf2a.domain.Categoria;
import com.github.lf2a.domain.Pedido;
import com.github.lf2a.domain.Produto;
import com.github.lf2a.repositories.CategoriaRepository;
import com.github.lf2a.repositories.PedidoRepository;
import com.github.lf2a.repositories.ProdutoRepository;
import com.github.lf2a.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>ProdutoService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Pedido.class.getName());
        });
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Categoria> categorias = categoriaRepository.findAllById(ids);

        return repo.search(nome, categorias, pageRequest);
    }
}
