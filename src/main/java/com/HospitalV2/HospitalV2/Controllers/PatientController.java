package com.HospitalV2.HospitalV2.Controllers;

import com.HospitalV2.HospitalV2.Repository.HRepository;
import com.HospitalV2.HospitalV2.Response.PatientLastNameResponse;
import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Service.HospitalService;
import com.HospitalV2.HospitalV2.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    HRepository hrepository;

    @Autowired
    HospitalService hospitalService;

    @GetMapping("/patients")
    public List<Patients> index(){
        return hrepository.findAll();

    }

    @GetMapping("/patients/{id}")
    public Patients show(@PathVariable Integer id) {
        Optional<Patients> patientFromDb = hrepository.findById(id);
        return patientFromDb.get();
    }

    @GetMapping("/patients/age/{age}")
    public ResponseEntity<PatientsListResponse> ageGreater(@PathVariable Integer age) {

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT)
                .body(hospitalService.filterByAge(age));
    }

    @GetMapping("/patients/lastName/{id}")
    public ResponseEntity<PatientLastNameResponse> lastName(@PathVariable Integer id) {
        Optional<Patients> patientFromDb = hrepository.findById(id);

        return ResponseEntity.status(HttpStatus.IM_USED)
                .body(new PatientLastNameResponse(patientFromDb.get()));
    }

  /*  @GetMapping("/patients/age/query/{age}")
    public List<Patients> ageWithQuery(@PathVariable Integer age) {
        return Repository.findByAgeGreaterThan(age);
    }*/





    @GetMapping("/patients/name/{firstName}")
    public Patients search(@PathVariable String firstName){
        return hrepository.findByFirstName(firstName);
    }

    @PostMapping("/patients/create")
    public Patients create(@RequestBody Map<String, String> body){
        String firstName = body.get("firstName");
        String lastName = body.get("lastName");
        String ageString = body.get("age");
        int age = Integer.parseInt(ageString);
        return (Patients) hrepository.save(new Patients(firstName, lastName, age));
    }



}
