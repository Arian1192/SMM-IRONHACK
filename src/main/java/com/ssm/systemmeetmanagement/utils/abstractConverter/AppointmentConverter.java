package com.ssm.systemmeetmanagement.utils.abstractConverter;
import com.ssm.systemmeetmanagement.model.Appointment;
import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.service.dto.AppointmentDto;
import com.ssm.systemmeetmanagement.service.dto.AttendeeDto;


import java.util.HashSet;
import java.util.Set;

public  class AppointmentConverter extends AbstractConverter<Appointment, AppointmentDto>{

    private final Set<AttendeeDto> attendeeDtoSet = new HashSet<>();


    @Override
    public Appointment fromDto(AppointmentDto dto) {
        return null;
    }

    @Override
    public AppointmentDto fromEntity(Appointment entity) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(entity.getId());
        appointmentDto.setDescription(entity.getDescription());
        appointmentDto.setLocation(entity.getLocation());
        appointmentDto.setTitle(entity.getTitle());
        appointmentDto.setDuration(entity.getDuration());
        appointmentDto.setLocalTime(entity.getTime());
        appointmentDto.setLocalDate(entity.getDate());
        appointmentDto.setOnline(entity.getIsOnline());
        appointmentDto.setOnsite(entity.getIsOnsite());
        if (entity.getHost() != null) {
            AttendeeDto hostDto = new AttendeeConverter().fromEntity(entity.getHost());
            appointmentDto.setHost(hostDto);
        } else {
            throw new IllegalStateException("El host de la cita no puede ser nulo");
        }
        Set<AttendeeDto> attendeesDto = new HashSet<>();
        if (entity.getAttendees() != null) {
            for (Attendee attendee : entity.getAttendees()) {
                AttendeeDto attendeeDto = new AttendeeConverter().fromEntity(attendee);
                attendeesDto.add(attendeeDto);
            }
        }
        appointmentDto.setAttendees(attendeesDto);
        return appointmentDto;
    }

}
