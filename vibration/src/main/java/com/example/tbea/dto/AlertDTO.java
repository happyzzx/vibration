package com.example.tbea.dto;

import lombok.Builder;
import lombok.Data;

public class AlertDTO {

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

    }

    @Data
    @Builder
    public static class DevPage {
        @Builder.Default
        private Integer page = 1; // 默认为1

        @Builder.Default
        private Integer pageSize = 10; // 默认为10

        private String start_time;

        private String end_time;

        private Integer dev_id;
    }
}
