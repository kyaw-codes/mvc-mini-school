package com.kyaw.demo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Embeddable
public class PersonalInfo {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Invalid Date of Birth")
    private LocalDate dob;
    @NotEmpty(message = "This Field cannot be empty!")
    private String nrcNo;
    @NotEmpty(message = "This Field cannot be empty!")
    private String fatherName;
    @NotEmpty(message = "This Field cannot be empty!")
    private String phoneNo;
    @NotEmpty(message = "This Field cannot be empty!")
    private String address;

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getNrcNo() {
        return nrcNo;
    }

    public void setNrcNo(String nrcNo) {
        this.nrcNo = nrcNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
