package com.yven.poc.controller;

import com.yven.poc.service.AmazonSNSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 7:57 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("/poc/sns")
@Slf4j
public class AmazonSNSController {

    @Autowired
    AmazonSNSService amazonSNSService;

    @Value("${aws.sns.topicArn}")
    private String topicArn;


    @PostMapping("publish")
    public String publish(@RequestBody Map<String, String> message) {

        // map 转 json string
        JSONObject jsonObject = new JSONObject(message);


        System.out.println("topicArn:" + topicArn);
        System.out.println("message:" + message);

        amazonSNSService.publish(topicArn, jsonObject.toString());
        return "done.";
    }

}
