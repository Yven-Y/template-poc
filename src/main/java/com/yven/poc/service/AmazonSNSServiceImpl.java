package com.yven.poc.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.yven.poc.config.AmazonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 7:59 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class AmazonSNSServiceImpl implements AmazonSNSService {

    @Autowired
    AmazonConfig amazonConfig;

    @Override
    public void publish(String topicArn, String message) {
        try {
            amazonConfig.getSNSClient().publish(topicArn, message);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }

        System.out.println("sns send done. topic:"+topicArn+",message:"+message);

    }
}
