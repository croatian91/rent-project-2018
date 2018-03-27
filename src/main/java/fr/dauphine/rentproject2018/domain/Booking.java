package fr.dauphine.rentproject2018.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Booking {
    private int id;
    private Date start;
    private Date end;
    private User user;
    private Product product;
    private Rent rent;
    private Configuration configuration;

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
    @NotNull
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
        Booking booking = (Booking) o;
        return id == booking.id &&
                Objects.equals(start, booking.start) &&
                Objects.equals(end, booking.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, start, end);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @OneToOne(mappedBy = "booking")
    @JsonIgnore
    public Rent getRent() {
        return rent;
    }

    @JsonProperty
    public void setRent(Rent rent) {
        this.rent = rent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "configuration_id", nullable = false)
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
