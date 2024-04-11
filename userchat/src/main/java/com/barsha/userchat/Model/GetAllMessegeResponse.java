package com.barsha.userchat.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllMessegeResponse {
    private String userID;
    private String name;
    private String messege;
}
