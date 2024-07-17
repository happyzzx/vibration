package com.example.tbea.service;

import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.AlertDTO;
import com.example.tbea.dto.VDataDTO;
import com.example.tbea.persitency.entity.Alert;
import com.example.tbea.persitency.entity.VData;
import com.example.tbea.persitency.entity.VDataResult;
import com.example.tbea.persitency.entity.VResult;

public interface AlertService {

    //根据id查询
    Result<JSONObject> getAlert(AlertDTO.Id alertIdDTO);

    //获取告警信息（分页）
    ResultTable<JSONObject> getALLAlert(AlertDTO.Page alertPageDTO);

    //查询设备告警信息（分页）
    ResultTable<JSONObject> getDevAlert(AlertDTO.DevPage alertDevPageDTO);


}
