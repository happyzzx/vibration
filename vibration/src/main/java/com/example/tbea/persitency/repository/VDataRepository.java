package com.example.tbea.persitency.repository;

import com.example.tbea.persitency.entity.User;
import com.example.tbea.persitency.entity.VData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface VDataRepository extends JpaRepository<VData, Long> {


    VData findVDataById(Long id);

    @Query("SELECT v FROM VData v WHERE v.devId = :devId " +
            "AND v.pointId = :pointId " +
            "AND (:startTime IS NULL OR v.time >= :startTime) " +
            "AND (:endTime IS NULL OR v.time <= :endTime) " +
            "ORDER BY v.time DESC")
    List<VData> findByDevIdAndPointIdAndTimeRange(
            @Param("devId") int devId,
            @Param("pointId") int pointId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    default List<VData> findByDevIdAndPointIdAndTimeRangeWithDefaults(int devId, int pointId, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        return findByDevIdAndPointIdAndTimeRange(devId, pointId,
                (startTime != null) ? startTime : LocalDateTime.of(1970, 1, 1, 0, 0),
                (endTime != null) ? endTime : currentTime);
    }


    @Query("SELECT v FROM VData v WHERE v.devId = :devId AND v.pointId = :pointId ORDER BY v.time DESC")
    Page<VData> findTopByDevIdAndPointIdOrderByTimeDesc(@Param("devId") int devId, @Param("pointId") int pointId, Pageable pageable);


}

