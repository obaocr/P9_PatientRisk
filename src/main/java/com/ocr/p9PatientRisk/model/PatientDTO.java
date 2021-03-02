package com.ocr.p9PatientRisk.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientDTO {

    private Integer Id;
    @NotBlank(message="Please enter familly name")
    @Size(min = 1, max = 30, message="familly name must be between 1 and 30 characters")
    private String familly;
    @NotBlank(message="Please enter given name")
    @Size(min = 1, max = 30, message="given name must be between 1 and 30 characters")
    private String given;
    @NotBlank(message="Please enter address")
    @Size(min = 1, max = 100, message="address must be between 1 and 100 characters")
    private String address;
    @NotBlank(message="Please enter phone")
    @Size(min = 9, max = 30, message="phone must be between 1 and 15 characters")
    private String phone;
    @NotBlank(message="Please enter the gender M/F")
    private String sex;
    @NotNull(message="Please enter the birth date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public PatientDTO() {
    }

    public PatientDTO(String familly, String given, String address, String phone, String sex, LocalDate birthDate) {
        this.familly = familly;
        this.given = given;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getFamilly() {
        return familly;
    }

    public void setFamilly(String familly) {
        this.familly = familly;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(String given) {
        this.given = given;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
