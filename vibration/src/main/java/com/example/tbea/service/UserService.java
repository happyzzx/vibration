package com.example.tbea.service;

import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.UserDTO;


public interface UserService {

    //新增
    Result<JSONObject> doAdd(UserDTO.Add userAddDTO);

//    //删除
//    Result<JSONObject> doDelete(UserDTO.Login userLoginDTO);
//
//    //修改
//    Result<JSONObject> doModify(UserDTO.Login userLoginDTO);
//
//    //获取用户信息（根据 id）
//    Result<JSONObject> doId(UserDTO.Login userLoginDTO);
//
//    //获取用户信息（分页）
//    ResultTable<JSONObject> doPage(UserDTO.Login userLoginDTO);
//
    //登录
    Result<JSONObject> doLogin(UserDTO.Login userLoginDTO);

}
