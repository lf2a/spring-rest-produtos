package com.github.lf2a.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import com.github.lf2a.services.exceptions.FileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import java.net.URI;

/**
 * <h1>S3Service.java</h1>
 * ---
 *
 * @author Luiz Filipy
 * @version 1.0
 * @since 23/03/2021
 */
@Service
public class S3Service {

    private Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3Client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();

            InputStream inputStream = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();

            return uploadFile(inputStream, fileName, contentType);
        } catch (IOException e) {
            throw new FileException("Erro de IO: " + e.getMessage());
        }
    }

    public URI uploadFile(InputStream inputStream, String fileName, String contentType) {
        try {
            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            LOG.info("Iniciando upload");
            s3Client.putObject(bucketName, fileName, inputStream, objectMetadata);
            LOG.info("Upload finalizado");
            return s3Client.getUrl(bucketName, fileName).toURI();
        } catch (Exception e) {
            throw new FileException("Erro ao converter URL para URI");
        }
    }
}
