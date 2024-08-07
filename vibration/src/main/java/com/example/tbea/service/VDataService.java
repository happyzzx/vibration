package com.example.tbea.service;

import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.UserDTO;
import com.example.tbea.dto.VDataDTO;
import com.example.tbea.persitency.entity.VData;
import com.example.tbea.persitency.entity.VDataResult;
import com.example.tbea.persitency.entity.VResult;

public interface VDataService {

    //根据id查询
    Result<JSONObject> getData(VDataDTO.Id vdataIdDTO);

    //获取数据信息（分页）
    ResultTable<JSONObject> getALLData(VDataDTO.Page vdataPageDTO);

    //根据chan_id查询最新数据
    Result<JSONObject> getLatest(VDataDTO.DevChanId vdataDevChanIdDTO);

    VDataResult createVDataResult(VData vData, VResult vResult);
}
