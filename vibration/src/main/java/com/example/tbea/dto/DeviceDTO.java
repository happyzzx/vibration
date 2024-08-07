package com.example.tbea.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class DeviceDTO {

    @Data
    public static class Id {
        private Long dev_id;
    }

    @Data
    @Builder
    public static class Page {
        @Builder.Default
        private Integer page = 1; // 默认为1

        @Builder.Default
        private Integer pageSize = 10; // 默认为10

    }

}
