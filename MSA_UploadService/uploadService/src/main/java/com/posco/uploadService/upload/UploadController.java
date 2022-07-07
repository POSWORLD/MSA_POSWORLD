package com.posco.uploadService.upload;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/img")
public class UploadController {

    @Value("${file}")
    private String filePath;

    @PostMapping("/upload")
    public ResponseEntity saveImg(@RequestParam MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("/img/"+file.getOriginalFilename());
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file is empty");
    }



}

