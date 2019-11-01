package com.spring.assistant.assistant.home.controller;


import com.spring.assistant.assistant.home.entity.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String homePageView(){
        return "index";
    }
    @RequestMapping(path = "/about",method = RequestMethod.GET)
    public String homePageLinkAboutPage(){
        return "about";
    }
    @RequestMapping("/request")
    public  String processDeneme(@ModelAttribute("userForm") Todo todo, org.springframework.ui.Model model, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            return "index";
        }
        else{

        }

        model.addAttribute("message", );
        return "index";
    }
    @RequestMapping(value = "/home-post", method = RequestMethod.POST) //Bu Html alınan post verileri backend iletiliyor
    public ResponseEntity denemes(@RequestParam("ss") String name){
        System.out.println(name);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
