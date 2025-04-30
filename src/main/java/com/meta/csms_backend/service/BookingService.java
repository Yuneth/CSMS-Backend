package com.meta.csms_backend.service;

import com.meta.csms_backend.dto.BookingRequest;
import com.meta.csms_backend.dto.BookingResponse;

import java.util.List;

public interface BookingService {
    BookingResponse createBooking(BookingRequest bookingRequest);

    BookingResponse updateBooking(Long id, BookingRequest bookingRequest);

    void deleteBooking(Long id);

    List<BookingResponse> getBookingsByUser(Long userId);

    BookingResponse getBookingById(Long id);


}