package com.itstnslv.s3imageupload.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AmazonConfig {

    @Autowired
    private Environment env;

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.access.secret}")
    private String accessSecret;

    @Bean
    public AmazonS3 s3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, accessSecret);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
