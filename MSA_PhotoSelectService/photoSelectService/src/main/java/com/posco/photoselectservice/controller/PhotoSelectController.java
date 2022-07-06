package com.posco.photoselectservice.controller;

import com.posco.photoselectservice.model.PhotoDto;
import com.posco.photoselectservice.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("photo")
public class PhotoSelectController {
    @Autowired
    PhotoService photoService;

    @Autowired
    PhotoDto photoDto;

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
