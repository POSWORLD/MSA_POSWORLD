package com.posco.photoSelectService.controller;

import com.posco.photoSelectService.model.PhotoDto;
import com.posco.photoSelectService.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("photo-select")
public class PhotoSelectController {
    @Autowired
    PhotoService photoService;

    @Autowired
    PhotoDto photoDto;

    @GetMapping("/")
    public Boolean test(){
        return true;
    }

    @GetMapping("/{userid}")
    public List<PhotoDto> getPhotoList(@PathVariable String userid){
        photoDto.setUserid(Integer.valueOf(userid));
        return photoService.getPhotoListByhomeId(photoDto);
    }

    @GetMapping("/detail/{id}")
    public PhotoDto getPhotoListById(@PathVariable String id){
        photoDto.setId(Integer.valueOf(id));
        return photoService.getPhotoById(photoDto);
    }
}