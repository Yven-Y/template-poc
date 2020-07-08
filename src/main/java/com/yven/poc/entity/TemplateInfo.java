package com.yven.poc.entity;

import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author ：yven
 * @date ：Created in 2020/5/26 8:51 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Entity(name = "miot_product_s3_template_info")
@Data
public class TemplateInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thirdPartyName;
    private String categoryName;
    private String productKey;
    private String templateType;

    @Column(name = "s3_version_id")
    private String s3VersionId;

    @Column(name = "s3_addr")
    private String s3Addr;

    private Integer available;
    private Date createTime;
    private Date updateTime;
}
