package com.example.tbea.persitency.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Entity
@Table(name = "Device")
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //设备名称
    private String name;

    //布点位置
    private String location;

    //设备型号
    private String type;

    @ElementCollection
    @CollectionTable(name = "device_point", joinColumns = @JoinColumn(name = "device_id"))
    private List<Point> point;
}
