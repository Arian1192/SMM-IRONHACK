package com.ssm.systemmeetmanagement.service.implementations;

import com.ssm.systemmeetmanagement.model.Attendee;
import com.ssm.systemmeetmanagement.model.User;
import com.ssm.systemmeetmanagement.repository.AttendeeRepository;
import com.ssm.systemmeetmanagement.repository.UserRepository;
import com.ssm.systemmeetmanagement.service.interfaces.IAttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendeeServiceImplementation implements IAttendeeService {

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Override
    public void save(Attendee user) {
        attendeeRepository.save(user);
    }

    @Override
    public List<Attendee> findAllAttendees() {
        return attendeeRepository.findAll();
    }

    @Override
    public Optional<Attendee> findUserById(Long id) {
        return attendeeRepository.findById(id);
    }


}
