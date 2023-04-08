package com.itstnslv.s3imageupload.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application.properties")
public class AmazonConfig {

    private final Environment env;

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.access.secret}")
    private String accessSecret;

    public AmazonConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public AmazonS3 s3() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                System.getenv(accessKey),
                System.getenv(accessSecret)
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.DEFAULT_REGION)
                .build();
    }

}
