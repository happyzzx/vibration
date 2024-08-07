package com.example.tbea.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tbea.dto.UserDTO;

@Log4j2
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result<JSONObject> add(@RequestBody UserDTO.Add userAddDT0) {
        return userService.doAdd(userAddDT0);
    }

    @PostMapping("/login")
    public Result<JSONObject> login(@RequestBody UserDTO.Login userloginDT0) {
        return userService.doLogin(userloginDT0);
    }

}