package fr.dauphine.rentproject2018.domain;

import javax.persistence.*;
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
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "daily_price")
    public float getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(float dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    @Basic
    @Column(name = "deposit")
    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "daily_forfeit")
    public float getDailyForfeit() {
        return dailyForfeit;
    }

    public void setDailyForfeit(float dailyForfeit) {
        this.dailyForfeit = dailyForfeit;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "defaults")
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
}
