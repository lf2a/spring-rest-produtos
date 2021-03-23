package com.github.lf2a;

import com.github.lf2a.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProdutosApplication implements CommandLineRunner {

    @Autowired
    private S3Service s3Service;

    public static void main(String[] args) {
        SpringApplication.run(SpringProdutosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        s3Service.uploadFile("/mnt/469AB54B9AB5386F/imagens/harmen-jelle-van-mourik-oSvK_3jlXGs-unsplash.jpg");
    }
}
