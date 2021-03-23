package com.github.lf2a.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

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

    public void uploadFile(String localFilePath) {

        try {
            var file = new File(localFilePath);
            LOG.info("Iniciando upload");
            s3Client.putObject(new PutObjectRequest(bucketName, "teste.jpg", file));
            LOG.info("Upload finalizado");
        } catch (AmazonServiceException e) {
            LOG.info("AmazonServiceException: " + e.getErrorMessage());
            LOG.info("Status code: " + e.getErrorCode());
        } catch (AmazonClientException e) {
            LOG.info("AmazonClientException: " + e.getMessage());
        }
    }
}
