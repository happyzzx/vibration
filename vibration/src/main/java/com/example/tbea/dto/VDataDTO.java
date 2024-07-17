package com.example.tbea.dto;

import lombok.Builder;
import lombok.Data;


@Data
public class VDataDTO {

    @Data
    public static class Id {
        private Long id;
    }

    @Data
    @Builder
    public static class Page {
        @Builder.Default
        private Integer page = 1; // 默认为1

        @Builder.Default
        private Integer pageSize = 10; // 默认为10
        private String start_time;
        private String end_time;
        private Integer dev_id;
        private Integer point_id;
    }

    @Data
    public static class DevChanId {
        private Integer dev_id;
        private Integer point_id;
    }
}
