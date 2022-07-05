package com.posco.photoselectservice.repository;

import com.posco.photoselectservice.model.PhotoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoDto, Integer> {

    List<PhotoDto> findAllById(Integer userid);

    PhotoDto findBypid(Integer id);
}
