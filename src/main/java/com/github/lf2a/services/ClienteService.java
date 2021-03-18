package com.github.lf2a.services;

import com.github.lf2a.domain.Categoria;
import com.github.lf2a.domain.Cliente;
import com.github.lf2a.dto.CategoriaDTO;
import com.github.lf2a.dto.ClienteDTO;
import com.github.lf2a.repositories.ClienteRepository;
import com.github.lf2a.services.exceptions.DataIntegrityException;
import com.github.lf2a.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h1>ClienteService.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 17/03/2021
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente find(Integer id) {
        return repo.findById(id).orElseThrow(() -> {
            throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Cliente.class.getName());
        });
    }

    public Cliente update(Cliente cliente) {
        var obj = find(cliente.getId());
        updateData(obj, cliente);
        return repo.save(obj);
    }

    public void delete(Integer id) {
        find(id);

        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um cliente que possui objetos relacionados a ele");
        }
    }

    public List<Cliente> findAll() {
        return repo.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        return repo.findAll(pageRequest);
    }

    public Cliente fromDto(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getId() , clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
