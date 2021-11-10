package com.cwsdg.cwsdg.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/gym/file-storage")
public class FileStorageController {


    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile[] file, @RequestParam("type") String type){

      log.info(String.valueOf(file));
    }

}
