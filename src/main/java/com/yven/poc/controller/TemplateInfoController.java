package com.yven.poc.controller;

import com.yven.poc.entity.TemplateInfo;
import com.yven.poc.repository.TemplateInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author ：yven
 * @date ：Created in 2020/5/26 9:02 PM
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RestController("/poc")
public class TemplateInfoController {

    @Autowired
    TemplateInfoRepository templateInfoRepository;


    @GetMapping("template/{id}")
    public TemplateInfo getTemplateInfo(@PathVariable(name = "id") Long id){
        Optional<TemplateInfo> templateInfo = templateInfoRepository.findById(id);
        return templateInfo.get();

    }
}
