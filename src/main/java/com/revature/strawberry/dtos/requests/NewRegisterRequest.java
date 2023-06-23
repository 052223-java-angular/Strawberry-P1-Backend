package com.revature.strawberry.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewRegisterRequest {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
