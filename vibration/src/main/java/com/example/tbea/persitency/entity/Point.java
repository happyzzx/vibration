package com.example.tbea.persitency.entity;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Point {

    private int point_id;
    private String name;
    private String signalType;

    // 你可以根据需要添加其他字段
}
