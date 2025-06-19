    package com.example.swp.controller;

    import com.example.swp.dto.request.SmokingStatusLogRequest;
    import com.example.swp.dto.response.SmokingStatusLogResponse;
    import com.example.swp.service.SmokingStatusLogService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/smoking-status-logs")
    public class SmokingStatusLogController {
        @Autowired
        private SmokingStatusLogService logService;

        @GetMapping
        public ResponseEntity<List<SmokingStatusLogResponse>> getAll() {
            return ResponseEntity.ok(logService.getAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<SmokingStatusLogResponse> getById(@PathVariable Integer id) {
            return ResponseEntity.ok(logService.getById(id));
        }

        @PostMapping
        public ResponseEntity<SmokingStatusLogResponse> create(@RequestBody SmokingStatusLogRequest request) {
            SmokingStatusLogResponse response = logService.create(request);
            return ResponseEntity.status(201).body(response);
        }

        @PutMapping("/{id}")
        public ResponseEntity<SmokingStatusLogResponse> update(@PathVariable Integer id, @RequestBody SmokingStatusLogRequest request) {
            return ResponseEntity.ok(logService.update(id, request));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
            logService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }