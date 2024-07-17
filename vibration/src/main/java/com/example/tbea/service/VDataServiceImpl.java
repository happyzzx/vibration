package com.example.tbea.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.tbea.common.Result;
import com.example.tbea.common.ResultTable;
import com.example.tbea.dto.VDataDTO;
import com.example.tbea.persitency.entity.VData;
import com.example.tbea.persitency.entity.VDataResult;
import com.example.tbea.persitency.entity.VResult;
import com.example.tbea.persitency.repository.VDataRepository;
import com.example.tbea.persitency.repository.VResultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VDataServiceImpl implements VDataService{

    private final VDataRepository vdataRepository;
    private final VResultRepository vresultRepository;

    @Autowired
    public VDataServiceImpl(VDataRepository vdataRepository, VResultRepository vresultRepository) {
        this.vdataRepository = vdataRepository;
        this.vresultRepository = vresultRepository;
    }

    public VDataResult createVDataResult(VData vData, VResult vResult) {
            VDataResult vDataResult = new VDataResult();

            // 设置 VData 的属性
            vDataResult.setDataId(vData.getId());
            vDataResult.setTime(vData.getTime());
            vDataResult.setDevId(vData.getDevId());
            vDataResult.setPointId(vData.getPointId());
            vDataResult.setAcceleration(vData.getAcceleration());
            // 设置 VResult 的属性
            vDataResult.setResultId(vResult.getId());
            vDataResult.setAmplitude(vResult.getAmplitude());
            vDataResult.setMean(vResult.getMean());
            vDataResult.setSd(vResult.getSd());
            vDataResult.setOhr(vResult.getOhr());
            vDataResult.setHfe(vResult.getHfe());
            vDataResult.setComplexity(vResult.getComplexity());
            vDataResult.setResult(vResult.getResult());

            return vDataResult;
    }



    public Result<JSONObject> getData(VDataDTO.Id vdataIdDTO){

        VData vdata = vdataRepository.findVDataById(vdataIdDTO.getId());

        if (vdata == null) {
            return Result.instance(1, "未找到符合条件的数据", null);
        }

        VResult vresult = vresultRepository.findByVdataId(vdata.getId());

        VDataResult data =  createVDataResult(vdata, vresult);

        // 创建 ObjectMapper 实例
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // 对象转换为 JSON 字符串
            String jsonString = objectMapper.writeValueAsString(data);

            // 将 JSON 字符串转换为 JSONObject
            JSONObject jsonObject = JSON.parseObject(jsonString);

            return Result.instance(0, "操作成功，查询结果如下", jsonObject);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return Result.instance(800, "服务器错误", null);
        }




    }


    public ResultTable<JSONObject> getALLData(VDataDTO.Page vdataPageDTO){

        // 根据需要传入 start_time 和 end_time 参数
        LocalDateTime startTime = (vdataPageDTO.getStart_time() != null) ? LocalDateTime.parse(vdataPageDTO.getStart_time()) : null;
        LocalDateTime endTime = (vdataPageDTO.getEnd_time() != null) ? LocalDateTime.parse(vdataPageDTO.getEnd_time()) : null;

        // 调用查询方法
        List<VData> list = vdataRepository.findByDevIdAndPointIdAndTimeRangeWithDefaults(
                vdataPageDTO.getDev_id(),
                vdataPageDTO.getPoint_id(),
                startTime,
                endTime
        );

        if (list == null || list.isEmpty()) {
            return ResultTable.instance(1, "未找到符合条件的数据", null, 0, 0, 0);
        } else {
            List<JSONObject> jsonList = new ArrayList<>();

            // 遍历每一个 VData，找到对应的 VResult 并合并为 VDataResult
            for (VData vdata : list) {
                VResult vresult = vresultRepository.findByVdataId(vdata.getId());
                VDataResult data = createVDataResult(vdata, vresult);

                // 创建 ObjectMapper 实例
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    // 将 VDataResult 对象转换为 JSON 字符串
                    String jsonString = objectMapper.writeValueAsString(data);

                    // 将 JSON 字符串转换为 JSONObject
                    JSONObject jsonObject = JSON.parseObject(jsonString);
                    jsonList.add(jsonObject);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return ResultTable.instance(800, "服务器错误", null, 0, 0, 0);
                }
            }

            return ResultTable.instance(0, "操作成功，查询结果如下", jsonList, jsonList.size(), vdataPageDTO.getPageSize(), vdataPageDTO.getPage());
        }


    }


    public Result<JSONObject> getLatest(VDataDTO.DevChanId vdataDevChanIdDTO){

        Pageable pageable = PageRequest.of(0, 1); // 只取第一页，每页一条数据
        // 使用 Spring Data JPA 查询方法，并传入 Pageable 对象
        Page<VData> vdataPage = vdataRepository.findTopByDevIdAndPointIdOrderByTimeDesc(vdataDevChanIdDTO.getDev_id(),vdataDevChanIdDTO.getPoint_id(),pageable);

        // 处理查询结果
        VData vdata = vdataPage.getContent().isEmpty() ? null : vdataPage.getContent().get(0);

        if (vdata == null) {
            return Result.instance(1, "未找到符合条件的数据", null);

        } else {

            VResult vresult = vresultRepository.findByVdataId(vdata.getId());

            VDataResult data =  createVDataResult(vdata, vresult);

            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // 对象转换为 JSON 字符串
                String jsonString = objectMapper.writeValueAsString(data);

                // 将 JSON 字符串转换为 JSONObject
                JSONObject jsonObject = JSON.parseObject(jsonString);

                return Result.instance(0, "操作成功，查询结果如下", jsonObject);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return Result.instance(800, "服务器错误", null);
            }
        }

    }

}
