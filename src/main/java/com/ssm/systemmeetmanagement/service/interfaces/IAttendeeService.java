package com.ssm.systemmeetmanagement.service.interfaces;

import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.Role;
import com.ssm.systemmeetmanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface IAttendeeService {
    void save(Attendee attendee);
    List<Attendee> findAllAttendees();
    Optional<Attendee> findUserById(Long id);
    void deleteById(Long id);
}
