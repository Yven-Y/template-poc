package com.yven.poc.service;

import org.springframework.stereotype.Service;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 7:58 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

public interface AmazonSNSService {

    void publish(String topicArn, String message);
}
