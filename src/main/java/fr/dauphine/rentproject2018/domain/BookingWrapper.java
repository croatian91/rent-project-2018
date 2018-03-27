package fr.dauphine.rentproject2018.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BookingWrapper {

    private Product product;
    private Date start;
    private Date end;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "BookingWrapper{" +
                "product=" + product +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
