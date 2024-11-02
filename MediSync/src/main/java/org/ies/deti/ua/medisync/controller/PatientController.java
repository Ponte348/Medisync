package org.ies.deti.ua.medisync.controller;

import java.util.List;
import java.util.Optional;

import org.ies.deti.ua.medisync.model.Medication;
import org.ies.deti.ua.medisync.model.Patient;
import org.ies.deti.ua.medisync.model.PatientWithVitals;
import org.ies.deti.ua.medisync.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.influxdb.query.FluxTable;


@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @GetMapping("/{id}")
    public Optional<PatientWithVitals> getPatientById(@PathVariable Long id) {
        Optional<PatientWithVitals> patient = patientService.getPatientWithVitalsById(id);
        return patient;

    }

    @GetMapping("/graph/{id}/{type}/{start}/{end}")
    public String getGraph(@PathVariable String id, @PathVariable String type, @PathVariable String start, @PathVariable String end) {
        String bedID = patientService.getPatientById(Long.parseLong(id)).get().getBed().getId().toString();
        List<FluxTable> tables = patientService.getPatientVitals(bedID, start, end);
        return patientService.generateQuickChartUrl(tables, bedID, type);
                    
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient updatedPatient) {
        try {
            Patient updated = patientService.updatePatient(id, updatedPatient);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.dischargePatient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/patients/{id}/medications")
    public ResponseEntity<List<Medication>> getMedications(@PathVariable Long id) {
        List<Medication> medications = patientService.getMedicationsByPatientId(id);
        return ResponseEntity.ok(medications);
    }

    @PostMapping("/patients/{id}/medications")
    public ResponseEntity<Patient> createMedication(@PathVariable Long id, @RequestBody Medication medication) {
        Patient patientWithMedication  = patientService.addMedication(id, medication);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientWithMedication);
    }

    @DeleteMapping("/patients/{id}/medications/{medicationId}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id, @PathVariable Long medicationId) {
        patientService.deleteMedication(id, medicationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/patients/{id}/medications/{medicationId}")
    public ResponseEntity<Patient> updateMedication(@PathVariable Long id, @PathVariable Long medicationId, @RequestBody Medication updatedMedication) {
        Patient patientWithUpdatedMedication = patientService.updateMedication(id, medicationId, updatedMedication);
        return ResponseEntity.ok(patientWithUpdatedMedication);
    }

}
