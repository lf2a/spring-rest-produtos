package com.github.lf2a.services;

import com.github.lf2a.domain.Cidade;
import com.github.lf2a.domain.Cliente;
import com.github.lf2a.domain.Endereco;
import com.github.lf2a.domain.enums.Perfil;
import com.github.lf2a.domain.enums.TipoCliente;
import com.github.lf2a.dto.ClienteDTO;
import com.github.lf2a.dto.ClienteNewDTO;
import com.github.lf2a.repositories.CidadeRepository;
import com.github.lf2a.repositories.ClienteRepository;
import com.github.lf2a.repositories.EnderecoRepository;
import com.github.lf2a.services.exceptions.AuthorizationException;
import com.github.lf2a.services.exceptions.DataIntegrityException;
import com.github.lf2a.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ImageService imageService;

    @Autowired
    private S3Service s3Service;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    public Cliente find(Integer id) {
        var userSs = UserService.authenticated();

        if (userSs == null || !userSs.hasRole(Perfil.ADMIN) && !id.equals(userSs.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        return repo.findById(id).orElseThrow(() -> {
            throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo:" + Cliente.class.getName());
        });
    }

    @Transactional
    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        cliente = repo.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;
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
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null, null);
    }

    public Cliente fromDto(ClienteNewDTO clienteNewDTO) {
        var cliente = new Cliente(null,
                clienteNewDTO.getNome(),
                clienteNewDTO.getEmail(),
                clienteNewDTO.getCpfOuCnpj(),
                TipoCliente.toEnum(clienteNewDTO.getTipo()), passwordEncoder.encode(clienteNewDTO.getSenha()));

        var cidade = cidadeRepository.
                findById(clienteNewDTO.getCidadeId())
                .orElseThrow(() -> {
                    throw new ObjectNotFoundException("Cidade não encontrada! Id:" + clienteNewDTO.getCidadeId() + ", Tipo:" + Cidade.class.getName());
                });
        ;

        var endereco = new Endereco(null,
                clienteNewDTO.getLogradouro(),
                clienteNewDTO.getNumero(),
                clienteNewDTO.getComplemento(),
                clienteNewDTO.getBairro(),
                clienteNewDTO.getCep(),
                cliente,
                cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteNewDTO.getTelefone1());

        if (clienteNewDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        }

        if (clienteNewDTO.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone3());
        }

        return cliente;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }

    public URI uploadProfilePicture(MultipartFile multipartFile) {

        var userSs = UserService.authenticated();

        if (userSs == null) {
            throw new AuthorizationException("Acesso negado");
        }

        var jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String fileName = String.format("%s%s.jpg", prefix, userSs.getId());

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }
}
