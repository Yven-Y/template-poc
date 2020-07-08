package com.yven.poc.enums;

import org.springframework.context.annotation.Description;

/**
 * @author ：yven
 * @date ：Created in 2020/5/28 2:06 PM
 * @description：不建议使用
 * @modified By：
 * @version: $version$
 */
@Deprecated
public enum RegionEnum {


    US_EAST_1("us-east-1", "us"),
    US_EAST_2("us-east-2", "us2"),
    EU_CENTRAL_1("eu-central-1", "eu"),
    AP_EAST_1("ap-east-1", "hk"),
    AP_SOUTH_1("ap-south-1", "mb"),
    AP_SOUTHEAST_1("ap-southeast-1", "sgp");


    private String name;
    private String flag;

    private RegionEnum(String name, String flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return this.name;
    }

    public String getFlag() {
        return this.flag;
    }


    public static String getRegionFlag(String name) {
        for (RegionEnum e : RegionEnum.values()) {

            if (e.getName().equals(name)) {
                return e.getFlag();
            }
        }
        return null;


    }

}
