package com.example.tbea.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.DeviceDTO;
import com.example.tbea.persitency.entity.Device;
import com.example.tbea.persitency.entity.VData;
import com.example.tbea.persitency.entity.VDataResult;
import com.example.tbea.persitency.entity.VResult;
import com.example.tbea.persitency.repository.DeviceRepository;
import com.example.tbea.persitency.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Result<JSONObject> getDevice(DeviceDTO.Id deviceIdDTO){

        if (deviceRepository.findDeviceById(deviceIdDTO.getDev_id()) == null) {
            return Result.instance(1, "未找到符合条件的数据", null);
        } else {
            Device device = deviceRepository.findDeviceById(deviceIdDTO.getDev_id());

            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // 对象转换为 JSON 字符串
                String jsonString = objectMapper.writeValueAsString(device);

                // 将 JSON 字符串转换为 JSONObject
                JSONObject jsonObject = JSON.parseObject(jsonString);

                return Result.instance(0, "操作成功，查询结果如下", jsonObject);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Result.instance(800, "服务器错误", null);
            }
        }

    }


    public ResultTable<JSONObject> getAllDevices(DeviceDTO.Page devicePageDTO) {


        List<Device> list = deviceRepository.findAll();

        if (list == null || list.isEmpty()) {
            return ResultTable.instance(1, "设备列表为空，请先添加设备", null, 0, 0, 0);
        } else {

            ObjectMapper objectMapper = new ObjectMapper();
            List<JSONObject> jsonList = list.stream()
                    .map(device -> objectMapper.convertValue(device, JSONObject.class))
                    .collect(Collectors.toList());

            return ResultTable.instance(0, "操作成功，查询结果如下", jsonList, jsonList.size(), devicePageDTO.getPageSize(), devicePageDTO.getPage());
        }


    }
}