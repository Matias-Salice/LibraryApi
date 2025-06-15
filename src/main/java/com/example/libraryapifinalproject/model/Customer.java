package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    public Customer(String username, String password, String phoneNumber) {
        super(username, password);
        this.phoneNumber = phoneNumber;
    }

    public void addBooking(Reservation reservation) {
        reservations.add(reservation);
        reservation.setCustomer(this);
    }

    public void removeBooking(Reservation reservation) {
        reservations.remove(reservation);
        reservation.setCustomer(null);
    }
}