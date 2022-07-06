package com.example.homeService.controller;

import com.example.homeService.model.HomeDto;
import com.example.homeService.service.HomeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeServiceImpl homeService;


    @GetMapping("/{id}")
    public HomeDto getHome(@PathVariable String id){
        return homeService.getHome(Integer.parseInt(id));

    }
    @PostMapping("/")
    public Integer InsertHome(@RequestBody HomeDto homeDto){
        HomeDto result = null;
        try{
            homeDto.setUserid(homeDto.getUserid());
            homeDto.setTitle("미니룸");
            homeDto.setPhoto("좋은사진");
            homeDto.setContent("좋은주말");
            homeDto.setBgm("좋은음악");

            result = homeService.insertHome(homeDto);
        }catch (Exception e){

        }
        if(result != null){
            return 1;
        }
        return 0;
    }

    @PutMapping("/update/{id}")
    public Integer updateHome(@RequestBody HomeDto homeDto, @PathVariable String id) {
        homeDto.setId(Integer.valueOf(id));
        if( homeService.updateHome(homeDto)!=null){
            return 1;
        }else{
            return 0;
        }
    }



}