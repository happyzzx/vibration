package com.example.tbea.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.DeviceDTO;
import com.example.tbea.dto.VDataDTO;
import com.example.tbea.service.DeviceService;
import com.example.tbea.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(path = "/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/inquire")
    public Result<JSONObject> inquire(@RequestBody DeviceDTO.Id deviceIdDTO) {
        return deviceService.getDevice(deviceIdDTO);
    }

    @PostMapping("/page")
    public ResultTable<JSONObject> page(@RequestBody DeviceDTO.Page devicePageDTO) {
        return deviceService.getAllDevices(devicePageDTO);
    }

}
