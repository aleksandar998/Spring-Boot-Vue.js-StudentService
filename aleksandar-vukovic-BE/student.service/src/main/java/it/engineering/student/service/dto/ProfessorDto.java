package it.engineering.student.service.dto;

import it.engineering.student.service.entity.City;
import it.engineering.student.service.entity.Title;

import java.util.Date;
import java.util.Objects;

public class ProfessorDto {
    private Long professorId;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private City city;
    private String phone;
    private Date reelectionDate;
    private Title title;

    public ProfessorDto(Long professorId, String firstname, String lastname, String email, String address, City city, String phone, Date reelectionDate, Title title) {
        this.professorId = professorId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.reelectionDate = reelectionDate;
        this.title = title;
    }

    public ProfessorDto() {
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getReelectionDate() {
        return reelectionDate;
    }

    public void setReelectionDate(Date reelectionDate) {
        this.reelectionDate = reelectionDate;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorDto that = (ProfessorDto) o;
        return Objects.equals(professorId, that.professorId) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(phone, that.phone) && Objects.equals(reelectionDate, that.reelectionDate) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, firstname, lastname, email, address, city, phone, reelectionDate, title);
    }
}
