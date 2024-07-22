package com.AdaSigorta.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String tcKimlikNo;
    private String telNo;
    private String email;
    private String il;
    private String ilce;
    private LocalDate dogumTarihi;


    public Customer(){}
    public Customer(Long id, String name, String surname,
                    String tcKimlikNo, String telNo,
                    String email, String il, String ilce,
                    LocalDate dogumTarihi) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tcKimlikNo = tcKimlikNo;
        this.telNo = telNo;
        this.email = email;
        this.il = il;
        this.ilce = ilce;
        this.dogumTarihi = dogumTarihi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(String tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public String getIlce() {
        return ilce;
    }

    public void setIlce(String ilce) {
        this.ilce = ilce;
    }

    public LocalDate getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(LocalDate dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(tcKimlikNo, customer.tcKimlikNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tcKimlikNo);
    }

    // Override toString
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tcKimlikNo='" + tcKimlikNo + '\'' +
                ", telNo='" + telNo + '\'' +
                ", email='" + email + '\'' +
                ", il='" + il + '\'' +
                ", ilce='" + ilce + '\'' +
                ", dogumTarihi=" + dogumTarihi +
                '}';
    }
}
