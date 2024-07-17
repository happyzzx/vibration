package com.example.tbea.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.dto.UserDTO;
import com.example.tbea.persitency.entity.User;
import com.example.tbea.persitency.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public Result<JSONObject> doLogin(UserDTO.Login userLoginDTO) {

        User user = userRepository.findUserByUsername(userLoginDTO.getUsername());
        if (user == null) {
            return Result.instance(1,"用户名或密码错误",null);
        }

        if (passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            // todo: jwt

            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // 将 User 对象转换为 JSON 字符串
                String jsonString = objectMapper.writeValueAsString(user);

                // 将 JSON 字符串转换为 JSONObject
                JSONObject jsonObject = JSON.parseObject(jsonString);

                return Result.instance(0, "登录成功", jsonObject);
            }catch(JsonProcessingException e) {
                e.printStackTrace();
                return Result.instance(1, "服务器错误", null);
            }
        } else {
            return Result.instance(1,"用户名或密码错误",null);
        }
    }





    public Result<JSONObject> doAdd(UserDTO.Add userAddDTO){

        if (userRepository.findUserByUsername(userAddDTO.getUsername()) == null) {

            User user = new User();
            user.setUsername(userAddDTO.getUsername());
            user.setRealname(userAddDTO.getRealname());
            user.setMobile(userAddDTO.getMobile());
            user.setPassword(userAddDTO.getPassword());
            userRepository.save(user);

            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // 将 User 对象转换为 JSON 字符串
                String jsonString = objectMapper.writeValueAsString(user);

                // 将 JSON 字符串转换为 JSONObject
                JSONObject jsonObject = JSON.parseObject(jsonString);

                return Result.instance(0, "添加成功", jsonObject);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Result.instance(800, "服务器错误", null);
            }
        } else {
            return Result.instance(1, "该用户名已被添加注册，请直接登录或更换用户名", null);
        }


    }

}
