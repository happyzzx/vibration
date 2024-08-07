package com.example.tbea.persitency.repository;

import com.example.tbea.persitency.entity.Alert;
import com.example.tbea.persitency.entity.VData;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {

    Alert findAlertById(Long id);

    @Query("SELECT a FROM Alert a WHERE (:startTime IS NULL OR a.time >= :startTime) " +
            "AND (:endTime IS NULL OR a.time <= :endTime) " +
            "ORDER BY a.time DESC")
    List<Alert> findByTimeRange(
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    default List<Alert> findByTimeRangeWithDefaults(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        return findByTimeRange(
                (startTime != null) ? startTime : LocalDateTime.of(1970, 1, 1, 0, 0),
                (endTime != null) ? endTime : currentTime
        );
    }

    @Query("SELECT a FROM Alert a WHERE a.devId = :devId " +
            "AND (:startTime IS NULL OR a.time >= :startTime) " +
            "AND (:endTime IS NULL OR a.time <= :endTime) " +
            "ORDER BY a.time DESC")
    List<Alert> findByDevIdAndTimeRange(
            @Param("devId") int devId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    default List<Alert> findByDevIdAndTimeRangeWithDefaults(int devId, LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        return findByDevIdAndTimeRange(
                devId,
                (startTime != null) ? startTime : LocalDateTime.of(1970, 1, 1, 0, 0),
                (endTime != null) ? endTime : currentTime
        );
    }


}
