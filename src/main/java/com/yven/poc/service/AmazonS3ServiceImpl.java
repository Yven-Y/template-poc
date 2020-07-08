package com.yven.poc.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.yven.poc.config.AmazonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 4:15 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {


    @Autowired
    AmazonConfig amazonConfig;


    @Override
    public Bucket createBucket(String bucket, Boolean enableVersion) {

        AmazonS3 s3 = amazonConfig.getS3client();
        Bucket b = null;
        if (s3.doesBucketExistV2(bucket)) {
            System.out.format("Bucket %s already exists.\n", bucket);
            b = getBucket(bucket);
        } else {
            boolean success = true;
            try {
                b = s3.createBucket(bucket);
            } catch (AmazonS3Exception e) {
                success = false;
                e.printStackTrace();
                System.err.println(e.getErrorMessage());
            }
            // 设置版本号可用
            if (enableVersion && success) {
                setBucketVersioning(bucket);

            }
        }
        return b;

    }

    @Override
    public void setBucketVersioning(String bucket) {
        BucketVersioningConfiguration configuration = new BucketVersioningConfiguration();
        configuration.setStatus(BucketVersioningConfiguration.ENABLED);
        SetBucketVersioningConfigurationRequest request = new SetBucketVersioningConfigurationRequest(bucket, configuration);
        amazonConfig.getS3client().setBucketVersioningConfiguration(request);

    }

    @Override
    public Bucket getBucket(String bucket) {
        Bucket named_bucket = null;
        List<Bucket> buckets = amazonConfig.getS3client().listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }

    @Override
    public PutObjectResult putObject(String bucket, String keyName, MultipartFile file) {
        PutObjectResult putObjectResult = null;
        try {
            String contentType = file.getContentType();
            long fileSize = file.getSize();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(contentType);
            objectMetadata.setContentLength(fileSize);

            putObjectResult = amazonConfig.getS3client().putObject(bucket, keyName, file.getInputStream(), objectMetadata);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("putObjectResult:" + putObjectResult);
        System.out.println("Done!");
        return putObjectResult;
    }

    @Override
    public void putObject(String bucket, String keyName, File file) {
        try {
            amazonConfig.getS3client().putObject(bucket, keyName, file);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        System.out.println("Done!");
    }

    @Override
    public List<Bucket> listBucket() {
        List<Bucket> buckets = amazonConfig.getS3client().listBuckets();
        return buckets;
    }

    @Override
    public void getObject(String bucket, String keyName) {
        try {
            S3Object o = amazonConfig.getS3client().getObject(bucket, keyName);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(keyName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Done!");

    }


}
