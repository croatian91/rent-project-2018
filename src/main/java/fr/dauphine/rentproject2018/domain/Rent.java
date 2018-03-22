package fr.dauphine.rentproject2018.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Rent {
    private int id;
    private Date start;
    private Date end;
    private Booking booking;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "start")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @Basic
    @Column(name = "end")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rent rent = (Rent) o;
        return id == rent.id &&
                Objects.equals(start, rent.start) &&
                Objects.equals(end, rent.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, start, end);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "booking_id")
    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
