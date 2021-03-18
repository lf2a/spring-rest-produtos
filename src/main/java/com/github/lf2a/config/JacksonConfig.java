package com.github.lf2a.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lf2a.domain.PagamentoComBoleto;
import com.github.lf2a.domain.PagamentoComCartao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * <h1>JacksonConfig.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 18/03/2021
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        var builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper om) {
                om.registerSubtypes(PagamentoComCartao.class);
                om.registerSubtypes(PagamentoComBoleto.class);
                super.configure(om);
            }
        };
        return builder;
    }
}
