package com.example.tbea.persitency.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;  // 只导入java.sql.Timestamp
import java.util.List;



@Entity
@Table(name = "VDATA")
@Data
public class VData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dev_id", nullable = false)
    private int devId;

    @Column(name = "point_id", nullable = false)
    private int pointId;

    //1设备唯一识别码
    @JsonIgnore
    @Column(name = "v_device", nullable = false)
    private String vDevice;

    //2通道号
    @JsonIgnore
    private int channel;

    //3加速度单位
    @JsonIgnore
    private String unit;

    //4采样频率
    @JsonIgnore
    private int sampling;

    //5频率分辨率
    @JsonIgnore
    private Float freqResolution;

    //6峰值
    @JsonIgnore
    private Float peak;

    //7谷值
    @JsonIgnore
    private Float valley;

    //8峰峰值
    @JsonIgnore
    private Float peakpeak;

    //9均值
    @JsonIgnore
    private Float meaning;

    //10有效值
    @JsonIgnore
    private Float rms;

    //11碰撞返回值
    @JsonIgnore
    private int collisionDetectionResult;

    //12时间戳
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;

    //13是否上传实时数据
    @JsonIgnore
    @Column(name = "is_rowData", nullable = false)
    private int isRowData;

    //14实时数据(加速度）
    @Column(columnDefinition = "LONGTEXT")
    private String acceleration;


}
