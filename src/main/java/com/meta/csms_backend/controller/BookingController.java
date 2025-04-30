package com.meta.csms_backend.controller;

import com.meta.csms_backend.dto.BookingRequest;
import com.meta.csms_backend.dto.BookingResponse;
import com.meta.csms_backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bookings")

public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsByUser(userId));
    }

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookingResponse> updateBooking(@PathVariable Long id, @RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.updateBooking(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
}
