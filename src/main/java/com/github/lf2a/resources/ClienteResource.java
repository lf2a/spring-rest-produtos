package com.github.lf2a.resources;

import com.github.lf2a.domain.Cliente;
import com.github.lf2a.dto.CategoriaDTO;
import com.github.lf2a.dto.ClienteDTO;
import com.github.lf2a.dto.ClienteNewDTO;
import com.github.lf2a.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>ClienteResource.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable("id") Integer id) {
        var obj = service.find(id);

        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteNewDTO) {
        var cliente = service.fromDto(clienteNewDTO);
        var obj = service.insert(cliente);

        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable("id") Integer id) {

        var cliente = service.fromDto(clienteDTO);
        cliente.setId(id);
        var obj = service.update(cliente);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll() {
        var clienteList = service.findAll();

        var catDto = clienteList
                .stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(catDto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        var clienteList = service.findPage(page, linesPerPage, orderBy, direction);

        var cliDto = clienteList.map(ClienteDTO::new);

        return ResponseEntity.ok().body(cliDto);
    }
}
