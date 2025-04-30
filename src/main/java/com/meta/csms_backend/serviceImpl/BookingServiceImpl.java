package com.meta.csms_backend.serviceImpl;

import com.meta.csms_backend.dto.BookingRequest;
import com.meta.csms_backend.dto.BookingResponse;
import com.meta.csms_backend.entity.Booking;
import com.meta.csms_backend.entity.ServiceEntity;
import com.meta.csms_backend.entity.User;
import com.meta.csms_backend.repository.BookingRepository;
import com.meta.csms_backend.repository.ServiceRepository;
import com.meta.csms_backend.repository.UserRepository;
import com.meta.csms_backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              ServiceRepository serviceRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        ServiceEntity serviceEntity = serviceRepository.findById(bookingRequest.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        Booking booking = new Booking();
        booking.setCustomerName(bookingRequest.getCustomerName());
        booking.setAddress(bookingRequest.getAddress());
        booking.setDateTime(bookingRequest.getDateTime());
        booking.setUser(user);
        booking.setService(serviceEntity);

        booking = bookingRepository.save(booking);
        return mapToResponse(booking);
    }

    @Override
    public BookingResponse updateBooking(Long id, BookingRequest bookingRequest) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        ServiceEntity serviceEntity = serviceRepository.findById(bookingRequest.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found"));

        booking.setCustomerName(bookingRequest.getCustomerName());
        booking.setAddress(bookingRequest.getAddress());
        booking.setDateTime(bookingRequest.getDateTime());
        //booking.setUser();
        booking.setService(serviceEntity);

        booking = bookingRepository.save(booking);
        return mapToResponse(booking);
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {
        List<Booking> bookings = bookingRepository.findByUserId(userId);
        return bookings.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToResponse(booking);
    }

    private BookingResponse mapToResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getCustomerName(),
                booking.getAddress(),
                booking.getDateTime(),
                booking.getService().getName(),
                booking.getUser().getUsername()
        );
    }
}