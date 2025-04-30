package com.meta.csms_backend.controller;

import com.meta.csms_backend.dto.ServiceRequest;
import com.meta.csms_backend.dto.ServiceResponse;
import com.meta.csms_backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(originPatterns = "*")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @PostMapping
    public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceRequest request) {
        return ResponseEntity.ok(serviceService.createService(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse> updateService(@PathVariable Long id, @RequestBody ServiceRequest request) {
        return ResponseEntity.ok(serviceService.updateService(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.ok("Service deleted successfully");
    }
}
