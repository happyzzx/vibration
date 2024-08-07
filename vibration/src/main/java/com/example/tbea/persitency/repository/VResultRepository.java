package com.example.tbea.persitency.repository;

import com.example.tbea.persitency.entity.VResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface VResultRepository extends JpaRepository<VResult, Long> {

    // 根据 vDataId 查找 VResult
    VResult findByVdataId(Long vDataId);  // 确保方法名与字段名匹配
}