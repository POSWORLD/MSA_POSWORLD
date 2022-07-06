package com.example.homeService.service;

import com.example.homeService.model.HomeDto;

public interface HomeService {


    HomeDto getHome(Integer id);
    HomeDto insertHome(HomeDto homeDto);
    HomeDto updateHome(HomeDto homeDto);




}