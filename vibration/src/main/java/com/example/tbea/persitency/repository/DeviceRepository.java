package com.example.tbea.persitency.repository;


import com.example.tbea.persitency.entity.Device;
import com.example.tbea.persitency.entity.User;
import com.example.tbea.persitency.entity.VData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    Device findDeviceById(Long id);

    // 查询所有Device数据
    List<Device> findAll();
}
