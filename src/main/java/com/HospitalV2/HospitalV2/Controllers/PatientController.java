package com.HospitalV2.HospitalV2.Controllers;

import com.HospitalV2.HospitalV2.Repository.PatientsRepository;
import com.HospitalV2.HospitalV2.Response.PatientFirstNameOnlyResponse;
import com.HospitalV2.HospitalV2.Response.PatientLastNameResponse;
import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.GetPatientsByCharacterResponse;
import com.HospitalV2.HospitalV2.Service.HospitalService;
import com.HospitalV2.HospitalV2.Domain.Patients;
import org.aspectj.weaver.ResolvedPointcutDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    PatientsRepository hrepository;

    @Autowired
    HospitalService hospitalService;

    @GetMapping("/patients")
    public ResponseEntity<List<Patients>> index() {
        return ResponseEntity.status(HttpStatus.IM_USED).body(hrepository.findAll());

    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patients> show(@PathVariable Integer id) {

       return hospitalService.getPatientById(id);


    }


    @GetMapping("/patients/age/{age}")
    public ResponseEntity<PatientsListResponse> ageGreater(@PathVariable Integer age) {

        return hospitalService.getAgeGreaterThan(age);


    }

    @GetMapping("/patients/age/{age}/{age2}")
    public ResponseEntity<PatientsListResponse> ageInbetween(@PathVariable Integer age,
                                                             @PathVariable Integer age2) {
        return hospitalService.getPatientsBetweenAge(age, age2);

    }

    @GetMapping("/patients/lastName/{id}")
    public ResponseEntity<PatientLastNameResponse> lastName(@PathVariable Integer id) {
        return hospitalService.getPatientWithoutLastName(id);
    }


    @GetMapping("patients/deleteEntry/{id}")
    public ResponseEntity<Patients> deleteEntry(@PathVariable Integer id) {
        return hospitalService.deletePatient(id);
    }


    @GetMapping("/patients/name/response/{firstName}")
    public ResponseEntity<PatientFirstNameOnlyResponse> tryout(@PathVariable String firstName) {
        return hospitalService.getByFirstNameOnly(firstName);
    }

    @GetMapping("/patients/getByCharacter/{ch}")
    public ResponseEntity<GetPatientsByCharacterResponse> charSearch2(@PathVariable String ch) {
        return hospitalService.getByCharacter(ch);
    }

    @PutMapping("patients/update/{id}")
    public ResponseEntity<Patients> updatePatient(@RequestBody Patients patientsRequest, @PathVariable Integer id) {
        return hospitalService.updatePatient(patientsRequest, id);
    }

    @PostMapping("/patients/create")
    public ResponseEntity<Patients> create(@RequestBody Map<String, String> body) {
        return hospitalService.savePatient(body);
    }
}
