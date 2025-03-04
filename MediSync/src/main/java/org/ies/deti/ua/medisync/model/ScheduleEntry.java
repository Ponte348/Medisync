package org.ies.deti.ua.medisync.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "schedule_entry")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ScheduleEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_time", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime start_time;

    @Column(name = "end_time", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime end_time;

    @Column(name = "is_interval", nullable = false)
    private boolean isInterval;

    @ManyToMany(mappedBy = "scheduleEntries")
    @JsonIgnoreProperties({ "scheduleEntries" })
    private List<Room> rooms;

    @JsonProperty("roomsNumbers")
    public List<String> getRoomRoomNumbers() {
        return rooms.stream().map(Room::getRoomNumber).collect(Collectors.toList());
    }

    @ManyToOne
    @JoinColumn(name = "nurse_id", nullable = false)
    @JsonBackReference
    private Nurse nurse;

    // Constructors
    public ScheduleEntry() {
    }

    public ScheduleEntry(String timeSlot, boolean isInterval, List<Room> rooms, LocalDateTime startTime,
            LocalDateTime endTime) {
        this.start_time = startTime;
        this.end_time = endTime;
        this.isInterval = isInterval;
        this.rooms = rooms;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public boolean isInterval() {
        return isInterval;
    }

    public void setInterval(boolean isInterval) {
        this.isInterval = isInterval;
    }

    public List<Room> getRoom() {
        return rooms;
    }

    public void setRoom(List<Room> rooms) {
        this.rooms = rooms;
    }
}
