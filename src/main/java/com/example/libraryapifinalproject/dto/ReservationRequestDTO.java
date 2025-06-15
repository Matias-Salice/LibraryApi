package com.example.libraryapifinalproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequestDTO {
    private Long customerId;
    private String date;
    private String time;

}