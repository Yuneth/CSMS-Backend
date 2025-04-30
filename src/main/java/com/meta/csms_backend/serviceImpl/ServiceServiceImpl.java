package com.meta.csms_backend.serviceImpl;

import com.meta.csms_backend.dto.ServiceRequest;
import com.meta.csms_backend.dto.ServiceResponse;
import com.meta.csms_backend.entity.ServiceEntity;
import com.meta.csms_backend.repository.ServiceRepository;
import com.meta.csms_backend.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceResponse createService(ServiceRequest serviceRequest) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName(serviceRequest.getName());
        serviceEntity.setPrice(serviceRequest.getPrice());
        serviceEntity.setDescription(serviceRequest.getDescription());
        serviceEntity = serviceRepository.save(serviceEntity);
        return mapToResponse(serviceEntity);
    }

    @Override
    public ServiceResponse updateService(Long id, ServiceRequest serviceRequest) {
        ServiceEntity serviceEntity = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        serviceEntity.setName(serviceRequest.getName());
        serviceEntity.setPrice(serviceRequest.getPrice());
        serviceEntity.setDescription(serviceRequest.getDescription());
        serviceEntity = serviceRepository.save(serviceEntity);
        return mapToResponse(serviceEntity);
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public List<ServiceResponse> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponse getServiceById(Long id) {
        ServiceEntity serviceEntity = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return mapToResponse(serviceEntity);
    }

    private ServiceResponse mapToResponse(ServiceEntity serviceEntity) {
        return new ServiceResponse(
                serviceEntity.getId(),
                serviceEntity.getName(),
                serviceEntity.getPrice(),
                serviceEntity.getDescription()
        );
    }
}
