package it.engineering.student.service.dto;

import it.engineering.student.service.entity.City;

import java.io.Serializable;

public class StudentDto implements Serializable {
    private Long studentId;
    private String indexNumber;
    private String indexYear;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private City city;
    private int currentYearOfStudy;

    public StudentDto(Long studentId, String indexNumber, String indexYear, String firstname, String lastname, String email, String address, City city, int currentYearOfStudy) {
        this.studentId = studentId;
        this.indexNumber = indexNumber;
        this.indexYear = indexYear;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.city = city;
        this.currentYearOfStudy = currentYearOfStudy;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getIndexYear() {
        return indexYear;
    }

    public void setIndexYear(String indexYear) {
        this.indexYear = indexYear;
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

    public int getCurrentYearOfStudy() {
        return currentYearOfStudy;
    }

    public void setCurrentYearOfStudy(Long currentYearOfStudy) {
        this.currentYearOfStudy = Math.toIntExact(currentYearOfStudy);
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentId=" + studentId +
                ", indexNumber='" + indexNumber + '\'' +
                ", indexYear='" + indexYear + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city +
                ", currentYearOfStudy=" + currentYearOfStudy +
                '}';
    }
}
