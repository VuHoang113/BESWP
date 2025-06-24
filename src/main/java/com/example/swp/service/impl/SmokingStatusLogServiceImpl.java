package com.example.swp.service.impl;

import com.example.swp.dto.request.SmokingStatusLogRequest;
import com.example.swp.dto.response.SmokingStatusLogResponse;
import com.example.swp.entity.SmokingStatusLog;
import com.example.swp.entity.User;
import com.example.swp.repository.SmokingStatusLogRepository;
import com.example.swp.repository.UserRepository;
import com.example.swp.service.SmokingStatusLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class SmokingStatusLogServiceImpl implements SmokingStatusLogService {
    @Autowired
    private SmokingStatusLogRepository logRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SmokingStatusLogResponse> getAll() {
        return logRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public SmokingStatusLogResponse getById(Integer id) {
        return logRepository.findById(id).map(this::toResponse)
            .orElseThrow(() -> new IllegalArgumentException("Log not found"));
    }

    @Override
    public SmokingStatusLogResponse create(SmokingStatusLogRequest request) {
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if(Objects.equals(request.getLogDate(), LocalDate.now()) || !request.getLogDate().isEqual(LocalDate.now())) {
            throw  new RuntimeException("Logdate today is empty");
        }
        SmokingStatusLog log = new SmokingStatusLog();
        log.setUser(user);
        log.setLogDate(java.time.LocalDate.now());
        log.setCigarettesPerDay(request.getCigarettesPerDay());
        log.setSmokingFrequency(request.getSmokingFrequency());
        log.setCostPerPack(request.getCostPerPack());
        log.setNotes(request.getNotes());
        return toResponse(logRepository.save(log));
    }

    @Override
    public SmokingStatusLogResponse update(Integer id, SmokingStatusLogRequest request) {
        SmokingStatusLog log = logRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Log not found"));
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
            log.setUser(user);
        }
        if (request.getLogDate() != null) log.setLogDate(request.getLogDate());
        log.setCigarettesPerDay(request.getCigarettesPerDay());
        log.setSmokingFrequency(request.getSmokingFrequency());
        log.setCostPerPack(request.getCostPerPack());
        log.setNotes(request.getNotes());
        return toResponse(logRepository.save(log));
    }

    @Override
    public void delete(Integer id) {
        logRepository.deleteById(id);
    }

    private SmokingStatusLogResponse toResponse(SmokingStatusLog log) {
        SmokingStatusLogResponse res = new SmokingStatusLogResponse();
        res.setLogID(log.getLogID());
        res.setUserId(log.getUser().getUserID());
        res.setLogDate(log.getLogDate());
        res.setCigarettesPerDay(log.getCigarettesPerDay());
        res.setSmokingFrequency(log.getSmokingFrequency());
        res.setCostPerPack(log.getCostPerPack());
        res.setNotes(log.getNotes());
        return res;
    }
}