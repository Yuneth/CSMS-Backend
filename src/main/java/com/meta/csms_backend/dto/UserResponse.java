package com.meta.csms_backend.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String userToken;

    public UserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }


}
