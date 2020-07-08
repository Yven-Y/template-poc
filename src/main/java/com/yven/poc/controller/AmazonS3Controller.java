package com.yven.poc.controller;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.StringUtils;
import com.google.gson.Gson;
import com.yven.poc.entity.TemplateInfo;
import com.yven.poc.repository.TemplateInfoRepository;
import com.yven.poc.service.AmazonS3Service;
import com.yven.poc.service.AmazonSNSService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 4:04 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController
@RequestMapping("/poc/s3")
@Slf4j
public class AmazonS3Controller {


    @Autowired
    AmazonS3Service amazonS3Service;


    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${aws.sns.topicArn}")
    private String topicArn;


    @Autowired
    TemplateInfoRepository templateInfoRepository;


    @Autowired
    AmazonSNSService amazonSNSService;


    @PostMapping("buckets/create")
    public String createBuckets(@RequestParam(name = "bucket") String bucket, @RequestParam(name = "enable-version") Boolean enableVersion) {


        if (StringUtils.isNullOrEmpty(bucket)) {
            log.warn("未传递bucket，使用系统配置的bucket");
            bucket = this.bucket;
        }

        // 创建桶
        Bucket bucketStr = amazonS3Service.createBucket(bucket, enableVersion);
        log.info("bucketStr:{}", bucketStr);

        return "success!";
    }

    @PostMapping("buckets/upload")
    public String uploadFileToBuckets(@RequestParam("file") MultipartFile file, HttpServletRequest request) {


        // todo 上传才json文件需要格式压缩上传，减少空间
        // todo 相关参数需要前端传递

        String bucket = request.getParameter("bucket");
        if (StringUtils.isNullOrEmpty(bucket)) {
            log.warn("未传递bucket，使用系统配置的bucket");
            bucket = this.bucket;
        }


        String buzType = "voice-capability";
        String categoryName = request.getParameter("categoryName");
        String productKey = request.getParameter("productKey");
        String thirdPartType = request.getParameter("thirdPartyType");

        String myKey = buzType + "/" + categoryName + "/" + productKey + "/" + thirdPartType;


        String fileName = file.getOriginalFilename();
        myKey += "/" + fileName;

        log.info("bucket:" + bucket);
        log.info("myKey:" + myKey);


        // 1、上传文件到s3
        PutObjectResult putObjectResult = amazonS3Service.putObject(bucket, myKey, file);
        String versionId = putObjectResult.getVersionId();
        log.info("versionId:" + versionId);

        // 2、将 versionId 存储到mysql
        TemplateInfo templateInfo = new TemplateInfo();
        templateInfo.setCategoryName(categoryName);
        templateInfo.setProductKey(productKey);
        templateInfo.setS3VersionId(versionId);
        templateInfo.setThirdPartyName(thirdPartType);
        templateInfo.setS3Addr("");
        templateInfo.setAvailable(1);
        templateInfo.setCreateTime(new Date());
        templateInfo.setUpdateTime(new Date());


        String[] templateTypeArr = fileName.split("\\.");
        String templateType = templateTypeArr[0];

        templateInfo.setTemplateType(templateType);
        log.info("templateInfo:" + templateInfo.toString());


        TemplateInfo templateInfoResult = templateInfoRepository.save(templateInfo);
        log.info("templateInfoResult:" + templateInfoResult.toString());


        // 3、发送sns通知
        Map<String, String> messageMap = new HashMap<>();
        messageMap.put("templateChangedStatus", "one");
        messageMap.put("thirdPartyName", thirdPartType);
        messageMap.put("categoryName", categoryName);
        messageMap.put("productKey", productKey);
        messageMap.put("templateType", templateType);

        JSONObject jsonObject = new JSONObject(messageMap);
        amazonSNSService.publish(topicArn, jsonObject.toString());
        Gson gson = new Gson();

        return gson.toJson(templateInfoResult);
    }

}
