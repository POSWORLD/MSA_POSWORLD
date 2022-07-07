package com.posco.photoSelectService.repository;

import com.posco.photoSelectService.model.PhotoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoDto, Integer> {

    List<PhotoDto> findAllByUserid(Integer userid);

    Optional<PhotoDto> findById (Integer id);
}