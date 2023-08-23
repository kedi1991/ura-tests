package com.api.ura.demo.controller;

import com.api.ura.demo.model.URAModel;
import com.api.ura.demo.service.URAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.soap.SOAPException;
import java.io.IOException;

@Controller
public class URAController {

    //service method is called to pick the urs details in the model

    @Autowired
    URAService uraService;

    public URAController(URAService uraService) {
        this.uraService = uraService;
    }
    @GetMapping("user")
    public URAModel getTest(@RequestParam Integer id){
        return uraService.getService(id);
    }

    @GetMapping("ura")
    public  String getURA() throws SOAPException, IOException {
        return uraService.ReadPRNResponse();
    }

    @GetMapping("home")
    public greetResponse getHome(){
        return new greetResponse("okurut henry", 38, "bsc. cmpe") ;
    }
    @GetMapping("/")
    public String goHome(){
        return "index" ;
    }
    record greetResponse(String home, int age, String course){

    }
}
