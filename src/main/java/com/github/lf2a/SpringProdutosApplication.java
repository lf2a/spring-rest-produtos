package com.github.lf2a;

import com.github.lf2a.domain.Categoria;
import com.github.lf2a.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringProdutosApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(SpringProdutosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        repo.saveAll(Arrays.asList(cat1, cat2));
    }
}
