package com.example.tbea.persitency.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;  // 只导入java.sql.Timestamp
import java.util.List;



@Entity
@Table(name = "Alert")
@Data
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;

    @Column(name = "dev_id", nullable = false)
    private int devId;

    @Column(name = "point_id", nullable = false)
    private int pointId;

    //告警类型
    @Column(name = "alert_type", nullable = false)
    private String alertType;

    //告警值类型
    @Column(name = "alert_value", nullable = false)
    private String alertValue;

    //设备名称
    @Column(name = "dev_name", nullable = false)
    private String devName;

    //告警信息
    private String description;

}
