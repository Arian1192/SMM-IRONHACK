package com.ssm.systemmeetmanagement.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AppointmentDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate localDate;
    private LocalTime localTime;
    private String location;
    private boolean isOnline;
    private boolean isOnsite;
    private String duration;
    private AttendeeDto host;
    private Set<AttendeeDto> attendees;

}
