package com.posworld.photoService.service;

import com.posworld.photoService.model.PhotoDto;
import com.posworld.photoService.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public PhotoDto postPhoto(PhotoDto photoDto) {
        return photoRepository.save(photoDto);
    }

    public Integer deletePhoto(PhotoDto photoDto) {
        return photoRepository.deleteByIds(photoDto.getId(), photoDto.getUserid());
    }
}
