package com.github.lf2a.services.validation;

import com.github.lf2a.dto.ClienteDTO;
import com.github.lf2a.repositories.ClienteRepository;
import com.github.lf2a.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <h1>ClienteUpdateValidator.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 18/03/2021
 */
public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext constraintValidatorContext) {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        var cliente = clienteRepository.findByEmail(clienteDTO.getEmail());

        if (cliente != null && !cliente.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        list.forEach(f -> {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate(f.getMessage())
                    .addPropertyNode(f.getFieldName())
                    .addConstraintViolation();
        });

        return list.isEmpty();
    }
}
