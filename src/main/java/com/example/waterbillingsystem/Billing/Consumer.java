package com.example.waterbillingsystem.Billing;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "consumers")

public class Consumer {
    // all consumers details variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length=25, nullable = false, name = "name")
    private String name;
    @Column(nullable = false, unique = true, length = 40)
    private String contacts;
    @Column(nullable = false, unique = true, length = 40)
    private String email;
    @Column(length=200, nullable = false)
    private String password;
    @Column(length=40, nullable = false)
    private String province;
    @Column(nullable = false)
    private boolean status;
    @Column(nullable = true)
    private double unit;
    @Column(nullable = true)
    private double rate ;
    @Column(nullable = true)
    private double tax;
    @Column(nullable = true)
    private double discount;
    @Column(nullable = true)
    private double total;

//     = (unit * rate) + tax - discount


    public double calculateTotal() {
       return (unit*rate) + tax + discount;
    }



//----------------Getter and Setter--------------------------------------------


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContacts() {
        return contacts;
    }
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public double getUnit() {
        return unit;
    }
    public void setUnit(double unit) {
        this.unit = unit;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(double rate) {
        this.rate = rate;
    }
    public double getTax() {
        return tax;
    }
    public void setTax(double tax) {
        this.tax = tax;
    }
    public double getDiscount() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts='" + contacts + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", province='" + province + '\'' +
                ", status=" + status +
                ", unit=" + unit +
                ", rate=" + rate +
                ", tax=" + tax +
                ", discount=" + discount +
                ", total=" + total +
                '}';
    }


}
