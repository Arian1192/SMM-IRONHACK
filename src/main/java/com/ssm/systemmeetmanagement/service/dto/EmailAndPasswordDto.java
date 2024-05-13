package com.ssm.systemmeetmanagement.service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EmailAndPasswordDto {
    String email;
    String password;

}
