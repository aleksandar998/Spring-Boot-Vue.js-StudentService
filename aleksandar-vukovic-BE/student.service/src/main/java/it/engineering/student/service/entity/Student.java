package it.engineering.student.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", unique = true)
    private Long studentId;

    @Column(name = "index_number", unique = true, nullable = false, length = 5)
    private String indexNumber;

    @Column(name = "index_year", nullable = false, length = 4)
    private String indexYear;

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstname;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastname;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "address", length = 50)
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "current_year_of_study", nullable = false, length = 7)
    private int currentYearOfStudy;

    public Student(Long studentId, String indexNumber, String indexYear, String firstname, String lastname, String email, String address, City city, int currentYearOfStudy) {
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

    public Student() {
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

    public void setCurrentYearOfStudy(int currentYearOfStudy) {
        this.currentYearOfStudy = currentYearOfStudy;
    }

    @Override
    public String toString() {
        return "Student{" +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId)
                && Objects.equals(indexNumber, student.indexNumber)
                && Objects.equals(indexYear, student.indexYear)
                && Objects.equals(firstname, student.firstname)
                && Objects.equals(lastname, student.lastname)
                && Objects.equals(email, student.email)
                && Objects.equals(address, student.address)
                && Objects.equals(city, student.city)
                && Objects.equals(currentYearOfStudy, student.currentYearOfStudy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, indexNumber, indexYear, firstname, lastname, email, address, city, currentYearOfStudy);
    }
}
