package com.posco.photoSelectService.service;

import com.posco.photoSelectService.model.PhotoDto;
import com.posco.photoSelectService.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public List<PhotoDto> getPhotoListByhomeId(PhotoDto photoDto){
        return photoRepository.findAllById(photoDto.getUserid());
    }

    public PhotoDto getPhotoById(PhotoDto photoDto){
        return photoRepository.findByid(photoDto.getId());
    }



}
