package it.engineering.student.service.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "professor")
public class Professor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id", unique = true)
    private Long professorId;

    @Column(name = "professor_first_name", nullable = false)
    private String firstname;

    @Column(name = "professor_last_name", nullable = false)
    private String lastname;

    @Column(name = "professor_email", unique = true)
    private String email;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "reelection_date", nullable = false)
    private Date reelectionDate;

    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;

    public Professor(Long professorId, String firstname, String lastname, String email, String address, City city, String phone, Date reelectionDate, Title title) {
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

    public Professor() {
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
    public String toString() {
        return "Professor{" +
                "professorId=" + professorId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city +
                ", phone='" + phone + '\'' +
                ", reelectionDate=" + reelectionDate +
                ", title=" + title +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return Objects.equals(professorId, professor.professorId)
                && Objects.equals(firstname, professor.firstname)
                && Objects.equals(lastname, professor.lastname)
                && Objects.equals(email, professor.email)
                && Objects.equals(address, professor.address)
                && Objects.equals(city, professor.city)
                && Objects.equals(phone, professor.phone)
                && Objects.equals(reelectionDate, professor.reelectionDate)
                && Objects.equals(title, professor.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, firstname, lastname, email, address, city, phone, reelectionDate, title);
    }
}
