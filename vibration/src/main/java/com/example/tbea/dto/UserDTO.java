package com.example.tbea.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDTO {

    @Data
    public static class Login {
        private String username;

        private String password;
    }

    @Data
    public static class Add {

        private String username;

        private String password;

        private String realname;

        private String mobile;

    }

}
