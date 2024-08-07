package com.example.tbea.service;

import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.DeviceDTO;
import com.example.tbea.dto.VDataDTO;
import com.example.tbea.persitency.entity.Device;
import com.example.tbea.persitency.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface DeviceService {
    //根据dev_id查询
    Result<JSONObject> getDevice(DeviceDTO.Id deviceIdDTO);

    ResultTable<JSONObject> getAllDevices(DeviceDTO.Page devicePageDTO);

}
