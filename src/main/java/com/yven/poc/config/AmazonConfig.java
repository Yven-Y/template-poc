package com.yven.poc.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 4:36 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Configuration
public class AmazonConfig {


    @Value("${aws.s3.region}")
    private String region;


    /**
     * @return
     */
    @Bean
    public AmazonS3 getS3client() {

        Regions defaultRegion = Regions.fromName(this.region);
        return AmazonS3ClientBuilder.standard().withRegion(defaultRegion).build();
    }

    @Bean
    public AmazonSNS getSNSClient() {

        Regions defaultRegion = Regions.fromName(this.region);
        return AmazonSNSClientBuilder.standard().withRegion(defaultRegion).build();
    }


}
