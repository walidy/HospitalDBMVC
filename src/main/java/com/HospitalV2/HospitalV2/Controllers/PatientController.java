package com.HospitalV2.HospitalV2.Controllers;

import com.HospitalV2.HospitalV2.Repository.HRepository;
import com.HospitalV2.HospitalV2.Response.PatientFirstNameOnly;
import com.HospitalV2.HospitalV2.Response.PatientLastNameResponse;
import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.getPatientsByCharacter;
import com.HospitalV2.HospitalV2.Service.HospitalService;
import com.HospitalV2.HospitalV2.models.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
    public ResponseEntity<List<Patients>> index(){
        return ResponseEntity.status(HttpStatus.IM_USED).body(hrepository.findAll());

    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Patients> show(@PathVariable Integer id) {

        Patients patientToReturn;
        HttpStatus httpStatus = HttpStatus.IM_USED;
        Optional<Patients> patientFromDb = hrepository.findById(id);

        if (patientFromDb.isPresent()){
            patientToReturn = patientFromDb.get();
        } else {
            patientToReturn = null;
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus).body(patientToReturn);
    }

    @GetMapping("/patients/age/{age}")
    public ResponseEntity<PatientsListResponse> ageGreater(@PathVariable Integer age) {

        HttpStatus httpStatus = HttpStatus.I_AM_A_TEAPOT;

        List<Patients> filteredPatients = hospitalService.filterByAge(age).getPatientsList();

        if (filteredPatients.isEmpty()) { //getPatientList confusion
            httpStatus = HttpStatus.NOT_FOUND;}

        return ResponseEntity.status(httpStatus)
                .body(new PatientsListResponse(filteredPatients));
    }

    @GetMapping("/patients/age/{age}/{age2}")
    public ResponseEntity<PatientsListResponse> ageInbetween(@PathVariable Integer age,
                                                             @PathVariable Integer age2) {

        HttpStatus httpStatus = HttpStatus.OK;

        PatientsListResponse patientsListResponse = hospitalService.filterByAge2(age, age2);

        if (patientsListResponse.getPatientsList().isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus)
                    .body(patientsListResponse);
    }

    @GetMapping("/patients/lastName/{id}")
    public ResponseEntity<PatientLastNameResponse> lastName(@PathVariable Integer id) {
        Optional<Patients> patientFromDb = hrepository.findById(id);
        HttpStatus httpStatus = HttpStatus.IM_USED;
        Patients patients = patientFromDb.get(); // doesnt work
        if (patientFromDb == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus)
                .body(new PatientLastNameResponse(patientFromDb.get()));
    }

    @GetMapping("/patients/firstNameOnly/{id}")
    public ResponseEntity<PatientFirstNameOnly> fNameOnly(@PathVariable Integer id) {
        Optional<Patients> patientFromDb = hrepository.findById(id);

        return ResponseEntity.status(HttpStatus.IM_USED)
                .body(new PatientFirstNameOnly(patientFromDb.get()));
    }

  @GetMapping("patients/deleteEntry/{id}")
      public ResponseEntity<Patients> deleteEntry(@PathVariable Integer id){
      hrepository.deleteById(id);
       return ResponseEntity.status(HttpStatus.IM_USED).body(null);
  }



    @GetMapping("/patients/name/{firstName}")
    public ResponseEntity<Patients> search(@PathVariable String firstName){
      HttpStatus httpStatus = HttpStatus.IM_USED;
      if (hrepository.findByFirstName(firstName) == null){
          httpStatus = HttpStatus.NOT_FOUND;
      }
        return ResponseEntity.status(httpStatus).body(hrepository.findByFirstName(firstName));

    }
    @GetMapping("/patients/name/response/{firstName}")
    public ResponseEntity<Patients> tryout(@PathVariable String firstName){
      HttpStatus httpStatus = HttpStatus.IM_USED;
      if(hrepository.findByFirstName(firstName) == null){
          httpStatus = HttpStatus.NOT_FOUND;
      }
        return ResponseEntity.status(httpStatus).body(hrepository.findByFirstName(firstName));
    }

    @GetMapping("/patients/getByCharacter/{ch}")
    public ResponseEntity<getPatientsByCharacter> charSearch(@PathVariable String ch) {
      HttpStatus httpStatus = HttpStatus.IM_USED;

      if (hospitalService.getByCharacter(ch).getPatients().isEmpty()){  //confused.com
          httpStatus = HttpStatus.NOT_FOUND;
      }
        return ResponseEntity.status(httpStatus).body(hospitalService.getByCharacter(ch));
    }

    @PutMapping("patients/update/{id}")
    public ResponseEntity<Patients> updatePatient(@RequestBody Patients patientsRequest, @PathVariable Integer id){
      Optional<Patients> patientsO = hrepository.findById(id);

      if (!patientsO.isPresent()){
          return ResponseEntity.notFound().build();
      } else {
          Patients patientToUpdate = patientsO.get();

          patientToUpdate.setAge(patientsRequest.getAge());
          patientToUpdate.setFirstName(patientsRequest.getFirstName());
          patientToUpdate.setLastName((patientsRequest.getLastName()));

          hrepository.save(patientsO.get());
      }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/patients/create")
    public ResponseEntity<Patients> create(@RequestBody Map<String, String> body){
        Patients savedPatient = hospitalService.savePatient(body);
        return ResponseEntity.status(HttpStatus.OK).body(savedPatient);
    }
}
