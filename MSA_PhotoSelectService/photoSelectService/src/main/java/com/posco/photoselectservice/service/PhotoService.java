package com.posco.photoselectservice.service;

import com.posco.photoselectservice.model.PhotoDto;
import com.posco.photoselectservice.repository.PhotoRepository;
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
        return photoRepository.findBypid(photoDto.getId());
    }



}
