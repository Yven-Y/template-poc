## 用户配置 
AWS S3 、SNS的相关API调用，需要配置相关aws凭证，默认凭证配置文件–通常位于 ~/.aws/credentials；也可以在配置文件
中配置相关信息，但是获取服务的client方式不同
- AWS文档参考：https://docs.aws.amazon.com/zh_cn/sdk-for-java/v1/developer-guide/credentials.html


## 接口
### 桶创建
```/poc/s3/buckets/create```

### 文件上传
```/poc/s3/buckets/upload```

可使用 /resources/test_data/ 的测试数据

## S3桶、结构定义规则
桶名：```tcl-iot-{region}-{stage}```

stage: 
- feature 
- dev 
- test 
- pre 
- prod

```$xslt
 tcl-iot-south-1-test                       # S3桶名 tcl-iot-{region}-{stage}
    └── voice-capability               # 语音业务,能力
        ├── AC                         # 大品类
        │   └── hbRqOkoXPsQGinKQ       # 产品 product key
        │       └── google             # 第三方标识
        │           ├── init.json      # template文件 设备同步 
        │           ├── actions.json   # template文件 执行命令
        │           ├── commands.json  # 命令转换
        │           └── status.json    # 状态转换
        └── PW
            └── mOyYKqAXUORB86UO
                ├── alexa
                │   ├── init.json      # template文件 设备同步
                │   ├── actions.json
                │   ├── commands.json
                │   └── status.json
                └── google
                    ├── init.json      # template文件
                    ├── actions.json
                    ├── commands.json
                    └── status.json
```



## SNS 
SNS topic定义：```template_changed_event_{stage}```

SNS消息体：
```$xslt
{
    "templateChangedStatus": "one",
    "thirdPartName": "google",
    "categoryName": "AC",
    "productKey": "hbRqOkoXPsQGinKQ",
    "templateType": "commands"
}
```


## 参考
设计文档：
https://eagle-lab.atlassian.net/wiki/spaces/AWS/pages/144179235/Google-TCL+device+capability+conversion+template+en