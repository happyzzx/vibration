package com.example.tbea.persitency.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;
import java.sql.Timestamp;  // 只导入java.sql.Timestamp

@Data
public class VDataResult {

    private Long DataId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private int devId;
    private int pointId;
    private String acceleration;

    private Long ResultId;
    private String amplitude;
    private Float mean;
    private Float sd;
    private Float ohr;
    private Float hfe;
    private Float complexity;
    private int result;

}
