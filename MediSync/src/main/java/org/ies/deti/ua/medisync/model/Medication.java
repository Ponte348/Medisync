package org.ies.deti.ua.medisync.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medication")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "hour_interval", length = 255)
    private String hourInterval;

    @Column(name = "dosage", length = 255)
    private String dosage;

    @Column(name = "has_taken")
    private boolean hasTaken = false;

    @Column(name = "last_taken")
    private LocalDateTime lastTaken;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Patient patient;

    public Medication() {}

    public Medication(String name, String hourInterval, String dosage, Patient patient, LocalDateTime lastTaken, boolean hasTaken) {
        this.name = name;
        this.hourInterval = hourInterval;
        this.dosage = dosage;
        this.patient = patient;
        this.lastTaken = lastTaken;
        this.hasTaken = hasTaken;
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

    public String getHourInterval() {
        return hourInterval;
    }

    public void setHourInterval(String hourInterval) {
        this.hourInterval = hourInterval;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getLastTaken() {
        return lastTaken;
    }

    public void setLastTaken(LocalDateTime lastTaken) {
        this.lastTaken = lastTaken;
    }

    public boolean isHasTaken() {
        return hasTaken;
    }

    public void setHasTaken(boolean hasTaken) {
        this.hasTaken = hasTaken;
    }


}
