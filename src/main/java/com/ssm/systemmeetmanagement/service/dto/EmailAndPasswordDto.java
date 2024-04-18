package com.ssm.systemmeetmanagement.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
public class EmailAndPasswordDto {
    final String email;
    final String password;

}
