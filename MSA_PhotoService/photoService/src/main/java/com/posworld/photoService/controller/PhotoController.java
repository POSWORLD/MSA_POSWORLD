package com.posworld.photoService.controller;

import com.posworld.photoService.model.PhotoDto;
import com.posworld.photoService.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("photo")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @PostMapping("/{userid}")
    public PhotoDto postPhoto(@RequestBody PhotoDto photoDto, @PathVariable String userid) {
        PhotoDto result = null;
        try {
            photoDto.setUserid(Integer.valueOf(userid));
            photoDto.setTitle(photoDto.getTitle());
            photoDto.setImg(photoDto.getImg());
            photoDto.setContent(photoDto.getContent());
        } catch (Exception e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
//        HttpStatus httpStatus = (result != null )? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
//        return new ResponseEntity<>(httpStatus);
            return result = photoService.postPhoto(photoDto);
    }

    @DeleteMapping("/{id}/{userid}")
    public Integer deletePhoto(@PathVariable String id, @PathVariable String userid) {
        Integer result = null;
        PhotoDto photoDto = new PhotoDto();
        photoDto.setId(Integer.valueOf(id));
        photoDto.setUserid(Integer.valueOf(userid));
        result = photoService.deletePhoto(photoDto);

        return result;
    }
}
