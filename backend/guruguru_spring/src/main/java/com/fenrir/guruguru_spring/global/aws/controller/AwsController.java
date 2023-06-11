package com.fenrir.guruguru_spring.global.aws.controller;


import com.fenrir.guruguru_spring.global.aws.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/aws")
public class AwsController {

    private final S3Service awsService;
    private String path;

    @GetMapping("/s3/url")
    public Map<String, Serializable> getPresignedUrl(@RequestParam("filename") String filename) {
        System.out.println(filename);
        log.info("s3진입");

        path = "guruguru";
        String imgName = filename;

        return awsService.getPreSignedUrl(path,imgName);

    }
}
