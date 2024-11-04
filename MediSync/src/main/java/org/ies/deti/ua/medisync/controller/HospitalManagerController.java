package org.ies.deti.ua.medisync.controller;

import org.ies.deti.ua.medisync.model.Doctor;
import org.ies.deti.ua.medisync.model.Nurse;
import org.ies.deti.ua.medisync.model.Patient;
import org.ies.deti.ua.medisync.model.Room;
import org.ies.deti.ua.medisync.service.HospitalManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital")
public class HospitalManagerController {

    private final HospitalManagerService hospitalManagerService;

    @Autowired
    public HospitalManagerController(HospitalManagerService hospitalManagerService) {
        this.hospitalManagerService = hospitalManagerService;
    }

    // End point das salas

    @PostMapping("/rooms")
    public ResponseEntity<String> addRooms() {
        hospitalManagerService.addRooms();
        return ResponseEntity.status(HttpStatus.CREATED).body("Rooms added successfully\n");
    }

    @DeleteMapping("/rooms")
    public ResponseEntity<String> removeAllRooms() {
        try {
            hospitalManagerService.removeAllRooms();
            return ResponseEntity.ok("All rooms successfully removed\n");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error removing rooms: " + e.getMessage());
        }
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = hospitalManagerService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

}