package com.meta.csms_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookingResponse {
    private Long id;
    private String customerName;
    private String address;
    private LocalDateTime dateTime;
    private String serviceName;

    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BookingResponse(Long id, String customerName, String address, LocalDateTime dateTime, String serviceName, String username) {
        this.id = id;
        this.customerName = customerName;
        this.address = address;
        this.dateTime = dateTime;
        this.serviceName = serviceName;
        this.username = username;
    }
}