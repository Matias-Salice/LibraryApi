package model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String date;

    private String time;

    private Integer peopleCount;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Customer customer;
}