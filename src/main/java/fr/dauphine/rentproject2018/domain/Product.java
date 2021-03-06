package fr.dauphine.rentproject2018.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Product {
    private int id;
    private String label;
    private float dailyPrice;
    private float deposit;
    private float dailyForfeit;
    private String description;
    private String defaults;
    private Category category;
    private RentalPoint rentalPoint;
    private Collection<Booking> bookings;

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
    @Column(name = "label")
    @NotNull
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "daily_price")
    @NotNull
    public float getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    @Basic
    @Column(name = "deposit")
    @NotNull
    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "daily_forfeit")
    @NotNull
    public float getDailyForfeit() {
        return dailyForfeit;
    }

    public void setDailyForfeit(float dailyForfeit) {
        this.dailyForfeit = dailyForfeit;
    }

    @Basic
    @Column(name = "description", columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "defaults", columnDefinition = "TEXT")
    public String getDefaults() {
        return defaults;
    }

    public void setDefaults(String defaults) {
        this.defaults = defaults;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Float.compare(product.dailyPrice, dailyPrice) == 0 &&
                Float.compare(product.deposit, deposit) == 0 &&
                Float.compare(product.dailyForfeit, dailyForfeit) == 0 &&
                Objects.equals(label, product.label) &&
                Objects.equals(description, product.description) &&
                Objects.equals(defaults, product.defaults);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, label, dailyPrice, deposit, dailyForfeit, description, defaults);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_point_id", nullable = false)
    public RentalPoint getRentalPoint() {
        return rentalPoint;
    }

    public void setRentalPoint(RentalPoint rentalPoint) {
        this.rentalPoint = rentalPoint;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    @JsonIgnore
    public Collection<Booking> getBookings() {
        return bookings;
    }

    @JsonProperty
    public void setBookings(Collection<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", dailyPrice=" + dailyPrice +
                ", deposit=" + deposit +
                ", dailyForfeit=" + dailyForfeit +
                ", description='" + description + '\'' +
                ", defaults='" + defaults + '\'' +
                ", category=" + category +
                ", rentalPoint=" + rentalPoint +
                ", bookings=" + bookings +
                '}';
    }
}
