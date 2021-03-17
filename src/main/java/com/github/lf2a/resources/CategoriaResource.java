package com.github.lf2a.resources;

import com.github.lf2a.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <h1>CategoriaResource.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable("id") Integer id) {
        var obj = service.buscar(id);

        return ResponseEntity.ok().body(obj);
    }
}
