package com.example.demo.controllers;

import com.example.demo.bean.LoginRequestBean;
import com.example.demo.bean.RegisterRequestBean;
import com.example.demo.bean.ResponseBean;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import com.example.demo.util.MessageVarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created By Heshan
 * Created Date 6/6/2020
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseBean registerUser(@RequestBody RegisterRequestBean registerRequestBean){
        ResponseBean responseBean = new ResponseBean();

        try{
            responseBean = userService.registerUser(registerRequestBean);
        }
        catch (Exception e){
            responseBean.setResponseCode(MessageVarList.RES_ERROR);
            responseBean.setResponseMsg(e.getMessage());
        }

        return responseBean;
    }

    @PostMapping("/login")
    public ResponseBean loginUser(@RequestBody LoginRequestBean loginRequestBean){
        ResponseBean responseBean = new ResponseBean();

        try{
            responseBean = userService.loginUser(loginRequestBean);
        }
        catch (Exception ex){
            responseBean.setResponseCode(MessageVarList.RES_ERROR);
            responseBean.setResponseMsg(ex.getMessage());
        }

        return responseBean;
    }

}
