package com.github.lf2a;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProdutosApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringProdutosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
