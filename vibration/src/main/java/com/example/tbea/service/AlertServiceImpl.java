package com.example.tbea.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.AlertDTO;
import com.example.tbea.persitency.entity.Alert;
import com.example.tbea.persitency.entity.VData;
import com.example.tbea.persitency.entity.VDataResult;
import com.example.tbea.persitency.entity.VResult;
import com.example.tbea.persitency.repository.AlertRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{

    private final AlertRepository alertRepository;

    public AlertServiceImpl(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }


    //根据id查询
    public Result<JSONObject> getAlert(AlertDTO.Id alertIdDTO){

        Alert alert = alertRepository.findAlertById(alertIdDTO.getId());

        if (alert == null) {
            return Result.instance(1, "未找到符合条件的数据", null);
        }


        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 对象转换为 JSON 字符串
            String jsonString = objectMapper.writeValueAsString(alert);

            // 将 JSON 字符串转换为 JSONObject
            JSONObject jsonObject = JSON.parseObject(jsonString);

            return Result.instance(0, "操作成功，查询结果如下", jsonObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Result.instance(800, "服务器错误", null);
        }


    }


    //获取告警信息（分页）
    public ResultTable<JSONObject> getALLAlert(AlertDTO.Page alertPageDTO){

        // 根据需要传入 start_time 和 end_time 参数
        LocalDateTime startTime = (alertPageDTO.getStart_time() != null) ? LocalDateTime.parse(alertPageDTO.getStart_time()) : null;
        LocalDateTime endTime = (alertPageDTO.getEnd_time() != null) ? LocalDateTime.parse(alertPageDTO.getEnd_time()) : null;

        List<Alert> list = alertRepository.findByTimeRangeWithDefaults(startTime, endTime);

        if (list == null || list.isEmpty()) {
            return ResultTable.instance(1, "未找到符合条件的数据", null, 0, 0, 0);
        } else {
// 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // 创建用于存储转换结果的 List<JSONObject>
                List<JSONObject> jsonList = new ArrayList<>();
                for (Alert alert : list) {
                    // 对象转换为 JSON 字符串
                    String jsonString = objectMapper.writeValueAsString(alert);
                    // 将 JSON 字符串转换为 JSONObject
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    jsonList.add(jsonObject);
                }

                return ResultTable.instance(0, "操作成功，查询结果如下", jsonList, jsonList.size(), alertPageDTO.getPageSize(), alertPageDTO.getPage());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResultTable.instance(800, "服务器错误", null, 0, 0, 0);
            }

        }

    }


    //查询设备告警信息（分页）
    public ResultTable<JSONObject> getDevAlert(AlertDTO.DevPage alertDevPageDTO){

        // 根据需要传入 start_time 和 end_time 参数
        LocalDateTime startTime = (alertDevPageDTO.getStart_time() != null) ? LocalDateTime.parse(alertDevPageDTO.getStart_time()) : null;
        LocalDateTime endTime = (alertDevPageDTO.getEnd_time() != null) ? LocalDateTime.parse(alertDevPageDTO.getEnd_time()) : null;

        List<Alert> list = alertRepository.findByDevIdAndTimeRangeWithDefaults(alertDevPageDTO.getDev_id(),startTime, endTime);

        if (list == null || list.isEmpty()) {
            return ResultTable.instance(1, "未找到符合条件的数据", null, 0, 0, 0);
        } else {
// 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // 创建用于存储转换结果的 List<JSONObject>
                List<JSONObject> jsonList = new ArrayList<>();
                for (Alert alert : list) {
                    // 对象转换为 JSON 字符串
                    String jsonString = objectMapper.writeValueAsString(alert);
                    // 将 JSON 字符串转换为 JSONObject
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    jsonList.add(jsonObject);
                }

                return ResultTable.instance(0, "操作成功，查询结果如下", jsonList, jsonList.size(), alertDevPageDTO.getPageSize(), alertDevPageDTO.getPage());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return ResultTable.instance(800, "服务器错误", null, 0, 0, 0);
            }

        }

    }
}
