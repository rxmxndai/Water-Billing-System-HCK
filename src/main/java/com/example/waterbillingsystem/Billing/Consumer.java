package com.example.waterbillingsystem.Billing;
import javax.persistence.*;

@Entity
@Table(name = "consumers")

public class Consumer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length=25, nullable = false)
    private String name;



    @Column(nullable = false, unique = true, length = 40)
    private String contacts;

    @Column(length=20, nullable = false)
    private String password;

    @Column(length=40, nullable = false)
    private String province;

    @Column(nullable = false)
    private boolean status;

    private double unit;

    private double rate;

    private double tax;

    private double discount;



    //----------------Getter and Setter--------------------------------------------
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }





    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts='" + contacts + '\'' +
                ", password='" + password + '\'' +
                ", province='" + province + '\'' +
                ", status=" + status +
                ", unit=" + unit +
                ", rate=" + rate +
                ", tax=" + tax +
                ", discount=" + discount +
                '}';
    }

}
