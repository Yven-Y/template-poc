package com.yven.poc.service;


import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 4:13 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface AmazonS3Service {
    /**
     * 创建桶 ，首先会判断桶是否存在，不存在时才创建
     *
     * @param bucket
     * @param enableVersion 是否启用版本号
     * @return
     */
    Bucket createBucket(String bucket, Boolean enableVersion);


    /**
     * 设置bucket版本号可用
     *
     * @param bucket
     * @return
     */
    void setBucketVersioning(String bucket);


    /**
     * 获取桶相关信息
     *
     * @param bucket
     * @return
     */
    Bucket getBucket(String bucket);

    /**
     * @param bucket
     * @param keyName
     * @param file
     */
    PutObjectResult putObject(String bucket, String keyName, MultipartFile file);

    /**
     *
     * @param bucket
     * @param keyName
     * @param file
     */
    void putObject(String bucket, String keyName, File file);


    /**
     * @return
     */
    List<Bucket> listBucket();

    /**
     *
     * @param bucket
     * @param keyName
     */
    void getObject(String bucket, String keyName);
}
