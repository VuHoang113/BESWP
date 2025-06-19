package com.example.swp.controller;

    import com.example.swp.service.MembershipPackageService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import com.example.swp.dto.request.MembershipPackageRequest;
    import com.example.swp.dto.response.MembershipPackageResponse;

    import java.util.List;

    @RestController
    @RequestMapping("/membership-packages")
    public class MembershipPackageController {

        @Autowired
        private MembershipPackageService membershipPackageService;

        @PostMapping
        public ResponseEntity<MembershipPackageResponse> create(@RequestBody MembershipPackageRequest request) {
            MembershipPackageResponse response = membershipPackageService.create(request);
            return ResponseEntity.status(201).body(response);
        }

        @PutMapping("/{id}")
        public ResponseEntity<MembershipPackageResponse> update(@PathVariable Integer id, @RequestBody MembershipPackageRequest request) {
            MembershipPackageResponse response = membershipPackageService.update(id, request);
            return ResponseEntity.ok(response);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Integer id) {
            membershipPackageService.softDelete(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/{id}")
        public ResponseEntity<MembershipPackageResponse> getById(@PathVariable Integer id) {
            MembershipPackageResponse response = membershipPackageService.getById(id);
            return ResponseEntity.ok(response);
        }

        @GetMapping
        public ResponseEntity<List<MembershipPackageResponse>> getAll() {
            List<MembershipPackageResponse> responses = membershipPackageService.getAll();
            return ResponseEntity.ok(responses);
        }
    }