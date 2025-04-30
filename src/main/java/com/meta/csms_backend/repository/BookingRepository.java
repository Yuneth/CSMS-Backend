package com.meta.csms_backend.repository;

import com.meta.csms_backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find bookings by user id
    List<Booking> findByUserId (Long userId);

    // Find bookings by service id
    List<Booking> findByServiceId(Long serviceId);

}