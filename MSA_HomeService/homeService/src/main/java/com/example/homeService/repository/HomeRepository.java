package com.example.homeService.repository;

import com.example.homeService.model.HomeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends JpaRepository<HomeDto, Integer> {

}
