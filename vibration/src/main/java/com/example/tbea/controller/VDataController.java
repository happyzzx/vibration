package com.example.tbea.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.VDataDTO;
import com.example.tbea.service.VDataService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping(path = "/data")
public class VDataController {

    @Autowired
    private VDataService vdataService;

    @GetMapping("/inquire")
    public Result<JSONObject> inquire(@RequestBody VDataDTO.Id vdataIdDTO) {
        return vdataService.getData(vdataIdDTO);
    }

    @PostMapping("/page")
    public ResultTable<JSONObject> page(@RequestBody VDataDTO.Page vdataPageDTO) {
        return vdataService.getALLData(vdataPageDTO);
    }

    @GetMapping("/latest")
    public Result<JSONObject> latest(@RequestBody VDataDTO.DevChanId vdataDevChanIdDTO) {
        return vdataService.getLatest(vdataDevChanIdDTO);
    }

}
