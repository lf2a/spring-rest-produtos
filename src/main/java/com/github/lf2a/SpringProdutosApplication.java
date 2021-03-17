package com.github.lf2a;

import com.github.lf2a.domain.Categoria;
import com.github.lf2a.domain.Cidade;
import com.github.lf2a.domain.Estado;
import com.github.lf2a.domain.Produto;
import com.github.lf2a.repositories.CategoriaRepository;
import com.github.lf2a.repositories.CidadeRepository;
import com.github.lf2a.repositories.EstadoRepository;
import com.github.lf2a.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringProdutosApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository repo;

    @Autowired
    private ProdutoRepository repoProd;

    @Autowired
    private EstadoRepository repoEst;

    @Autowired
    private CidadeRepository repoCid;

    public static void main(String[] args) {
        SpringApplication.run(SpringProdutosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto prod1 = new Produto(null, "Computador", 2000.00);
        Produto prod2 = new Produto(null, "Impressora", 800.00);
        Produto prod3 = new Produto(null, "Mouse", 80.00);

        cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
        cat2.getProdutos().addAll(Arrays.asList(prod2));

        prod1.getCategorias().addAll(Arrays.asList(cat1));
        prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
        prod3.getCategorias().addAll(Arrays.asList(cat1));

        repo.saveAll(Arrays.asList(cat1, cat2));
        repoProd.saveAll(Arrays.asList(prod1, prod2, prod3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade c1 = new Cidade(null, "Uberlandia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(c1));
        est2.getCidades().addAll(Arrays.asList(c2, c3));

        repoEst.saveAll(Arrays.asList(est1, est2));
        repoCid.saveAll(Arrays.asList(c1, c2, c3));
    }
}
