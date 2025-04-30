package com.meta.csms_backend.service;

import com.meta.csms_backend.dto.ServiceRequest;
import com.meta.csms_backend.dto.ServiceResponse;

import java.util.List;

public interface ServiceService {
    List<ServiceResponse> getAllServices();
    ServiceResponse createService(ServiceRequest request);
    ServiceResponse updateService(Long serviceId, ServiceRequest request);
    void deleteService(Long serviceId);

    ServiceResponse getServiceById(Long serviceId);
}
