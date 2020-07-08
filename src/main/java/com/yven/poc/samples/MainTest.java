package com.yven.poc.samples;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.yven.poc.enums.RegionEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 3:08 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Slf4j
public class MainTest {

    public static void main(String[] args){
        // S3 test
//        Region usWest1 = Region.getRegion(Regions.US_EAST_1);
//        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
//
//        String regionFlag = RegionEnum.getRegionFlag(usWest1.getName());
//        String stage = "poc";
//
//        String bucketName = "tcl-iot-" + regionFlag +"-"+stage;
//
//        String buzType = "voice-capability";
//        String categoryName = "AC";
//        String productKey = "hbRqOkoXPsQGinKQ";
//        String  thirdPartType = "google";
//        String key = buzType + "/" + categoryName + "/" + productKey + "/" + thirdPartType;


//        Bucket b = CreateBucket.createBucket(s3,bucketName);
//        log.info(b.toString());



        String a = "actions.json";
        String[] aa = a.split("\\.");
        System.out.println(aa);





    }
}
