package com.example.tbea.persitency.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;  // 只导入java.sql.Timestamp
import java.util.List;



@Entity
@Table(name = "VRESULT")
@Data
public class VResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;

    @Column(name = "vdata_id", nullable = false, unique = true)
    private Long vdataId;

    //频谱振幅
    @Column(columnDefinition = "LONGTEXT")
    private String amplitude;

    //均值
    private Float mean;

    //标准差
    private Float sd;

    //奇偶谐波比
    private Float ohr;

    //高频能量占比
    private Float hfe;

    //频谱复杂度
    private Float complexity;

    //斩断结果
    private int result;

}
