package com.meta.csms_backend.repository;

import com.meta.csms_backend.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository <ServiceEntity, Long> {
    boolean existsByName (String name);
}
