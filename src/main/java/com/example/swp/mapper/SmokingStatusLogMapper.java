package com.example.swp.mapper;

import com.example.swp.dto.request.SmokingStatusLogRequest;
import com.example.swp.dto.response.SmokingStatusLogResponse;
import com.example.swp.entity.SmokingStatusLog;
import com.example.swp.entity.User;

public class SmokingStatusLogMapper {
    public static SmokingStatusLog toEntity(SmokingStatusLogRequest dto, User user) {
        SmokingStatusLog entity = new SmokingStatusLog();
        entity.setUser(user);
        entity.setLogDate(dto.getLogDate());
        entity.setCigarettesPerDay(dto.getCigarettesPerDay());
        entity.setSmokingFrequency(dto.getSmokingFrequency());
        entity.setCostPerPack(dto.getCostPerPack());
        entity.setNotes(dto.getNotes());
        return entity;
    }

    public static SmokingStatusLogResponse toResponse(SmokingStatusLog entity) {
        SmokingStatusLogResponse dto = new SmokingStatusLogResponse();
        dto.setLogID(entity.getLogID());
        dto.setUserId(entity.getUser() != null ? entity.getUser().getUserID() : null);
        dto.setLogDate(entity.getLogDate());
        dto.setCigarettesPerDay(entity.getCigarettesPerDay());
        dto.setSmokingFrequency(entity.getSmokingFrequency());
        dto.setCostPerPack(entity.getCostPerPack());
        dto.setNotes(entity.getNotes());
        return dto;
    }

    public static void updateEntity(SmokingStatusLog entity, SmokingStatusLogRequest dto, User user) {
        entity.setUser(user);
        entity.setLogDate(dto.getLogDate());
        entity.setCigarettesPerDay(dto.getCigarettesPerDay());
        entity.setSmokingFrequency(dto.getSmokingFrequency());
        entity.setCostPerPack(dto.getCostPerPack());
        entity.setNotes(dto.getNotes());
    }
}