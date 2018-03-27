package fr.dauphine.rentproject2018.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Configuration {
    private int id;
    private int N_DML;
    private int N_DmL;
    private int A_DJ;
    private int N_MOR;
    private int N_DMR;
    private int N_MOL;
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
    @Column(name = "N_DMaxL")
    @NotNull
    public int getN_DML() {
        return N_DML;
    }

    public void setN_DML(int n_DML) {
        N_DML = n_DML;
    }

    @Basic
    @Column(name = "N_DMinL")
    @NotNull
    public int getN_DmL() {
        return N_DmL;
    }

    public void setN_DmL(int n_DmL) {
        N_DmL = n_DmL;
    }

    @Basic
    @Column(name = "A_DJ")
    @NotNull
    public int getA_DJ() {
        return A_DJ;
    }

    public void setA_DJ(int a_DJ) {
        A_DJ = a_DJ;
    }

    @Basic
    @Column(name = "N_MOR")
    @NotNull
    public int getN_MOR() {
        return N_MOR;
    }

    public void setN_MOR(int n_MOR) {
        N_MOR = n_MOR;
    }

    @Basic
    @Column(name = "N_DMR")
    @NotNull
    public int getN_DMR() {
        return N_DMR;
    }

    public void setN_DMR(int n_DMR) {
        N_DMR = n_DMR;
    }

    @Basic
    @Column(name = "N_MOL")
    @NotNull
    public int getN_MOL() {
        return N_MOL;
    }

    public void setN_MOL(int n_MOL) {
        N_MOL = n_MOL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Configuration that = (Configuration) o;
        return id == that.id &&
                N_DML == that.N_DML &&
                N_DmL == that.N_DmL &&
                A_DJ == that.A_DJ &&
                N_MOR == that.N_MOR &&
                N_DMR == that.N_DMR &&
                N_MOL == that.N_MOL;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, N_DML, N_DmL, A_DJ, N_MOR, N_DMR, N_MOL);
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "configuration")
    @JsonIgnore
    public Collection<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Collection<Booking> bookings) {
        this.bookings = bookings;
    }
}
