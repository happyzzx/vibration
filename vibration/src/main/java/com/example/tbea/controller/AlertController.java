package com.example.tbea.controller;


import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.AlertDTO;
import com.example.tbea.dto.VDataDTO;
import com.example.tbea.persitency.entity.Alert;
import com.example.tbea.service.AlertService;
import com.example.tbea.service.VDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tbea.dto.UserDTO;
import com.example.tbea.persitency.repository.AlertRepository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private AlertRepository alertRepository;


    @PostMapping("/notify")
    public ResponseEntity<Map<String, Object>> receiveAlert(@RequestBody Alert alert) {
        // 保存告警信息到数据库
        alertRepository.save(alert);

        // 通过 WebSocket 转发告警信息到前端
        template.convertAndSend("/topic/alerts", alert);

        // 构造返回的 JSON 格式数据
        Map<String, Object> response = new HashMap<>();
        response.put("code", 0);
        response.put("message", "告警消息已成功接收并转发");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/inquire")
    public Result<JSONObject> inquire(@RequestBody AlertDTO.Id alertIdDTO) {
        return alertService.getAlert(alertIdDTO);
    }

    @PostMapping("/page")
    public ResultTable<JSONObject> page(@RequestBody AlertDTO.Page alertPageDTO) {
        return alertService.getALLAlert(alertPageDTO);
    }

    @PostMapping("/devpage")
    public ResultTable<JSONObject> devpage(@RequestBody AlertDTO.DevPage alertDevPageDTO) {
        return alertService.getDevAlert(alertDevPageDTO);
    }


}
