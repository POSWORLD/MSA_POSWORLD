package com.posco.photoSelectService.service;


import com.posco.photoSelectService.model.PhotoDto;
import com.posco.photoSelectService.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public List<PhotoDto> getPhotoListByhomeId(PhotoDto photoDto){
        return photoRepository.findAllByUserid(photoDto.getUserid());
    }

    public Optional<PhotoDto> getPhotoById(PhotoDto photoDto){
        return photoRepository.findById(photoDto.getId());
    }

}