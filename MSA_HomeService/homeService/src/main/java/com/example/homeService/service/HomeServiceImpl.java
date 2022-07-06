package com.example.homeService.service;

import com.example.homeService.model.HomeDto;
import com.example.homeService.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class HomeServiceImpl implements HomeService{
    @Autowired
    HomeRepository homeRepository;

    @Override
    public HomeDto getHome(Integer id) {
        return homeRepository.findById(id).get();
    }

    @Override
    public HomeDto insertHome(HomeDto homeDto) {
        return homeRepository.save(homeDto);
    }

    @Override
    public HomeDto updateHome(HomeDto homeDto) {
        HomeDto newHome = getHome(homeDto.getId());
        newHome.setTitle(homeDto.getTitle());
        newHome.setContent(homeDto.getContent());
        newHome.setPhoto(homeDto.getPhoto());
        return homeRepository.save(newHome);
    }



    @Transactional
    public void deleteHome(Integer id) {
        homeRepository.deleteById(id);
    }

}