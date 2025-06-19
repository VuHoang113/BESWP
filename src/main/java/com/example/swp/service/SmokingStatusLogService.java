package com.example.swp.service;

import com.example.swp.dto.request.SmokingStatusLogRequest;
import com.example.swp.dto.response.SmokingStatusLogResponse;
import java.util.List;

public interface SmokingStatusLogService {
    List<SmokingStatusLogResponse> getAll();
    SmokingStatusLogResponse getById(Integer id);
    SmokingStatusLogResponse create(SmokingStatusLogRequest request);
    SmokingStatusLogResponse update(Integer id, SmokingStatusLogRequest request);
    void delete(Integer id);
}