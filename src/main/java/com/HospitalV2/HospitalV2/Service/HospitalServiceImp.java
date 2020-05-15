package com.HospitalV2.HospitalV2.Service;

import com.HospitalV2.HospitalV2.Repository.PatientsRepository;
import com.HospitalV2.HospitalV2.Response.PatientFirstNameOnlyResponse;
import com.HospitalV2.HospitalV2.Response.PatientLastNameResponse;
import com.HospitalV2.HospitalV2.Response.PatientsListResponse;
import com.HospitalV2.HospitalV2.Response.GetPatientsByCharacterResponse;
import com.HospitalV2.HospitalV2.Domain.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class HospitalServiceImp implements HospitalService {

    @Autowired
    PatientsRepository repository;

    public PatientsListResponse filterByAge(int age) {
        List<Patients> patientsFromDb = repository.findAll();

        List<Patients> filteredList = patientsFromDb.stream().filter(patients -> patients.getAge() > age).collect(Collectors.toList());

        return new PatientsListResponse(filteredList);
    }



    public PatientsListResponse filterByAge2(int age, int age2) {
        List<Patients> patientsFromDb = repository.findAll();

        List<Patients> filteredList = patientsFromDb.stream().filter(patients -> patients.getAge() > age && patients.getAge() < age2).collect(Collectors.toList());

        return new PatientsListResponse(filteredList);
    }

    public ResponseEntity<Patients> getPatientById(Integer id){
        Patients patientToReturn;
        HttpStatus httpStatus = HttpStatus.IM_USED;
        Optional<Patients> patientFromDb = repository.findById(id);

        if (patientFromDb.isPresent()) {
            patientToReturn = patientFromDb.get();
        } else {
            patientToReturn = null;
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus).body(patientToReturn);
    }

    public ResponseEntity<PatientsListResponse> getAgeGreaterThan(Integer age){
        HttpStatus httpStatus = HttpStatus.I_AM_A_TEAPOT;

        List<Patients> filteredPatients = filterByAge(age).getPatientsList();

        if (filteredPatients.isEmpty()) { //getPatientList confusion
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus)
                .body(new PatientsListResponse(filteredPatients));
    }

    public ResponseEntity<Patients> deletePatient(Integer id){
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.IM_USED).body(null);
    }




    public ResponseEntity<Patients> savePatient(Map<String, String> body) {
        Patients savedPatient = repository.save(new Patients(body.get("firstName"), body.get("lastName"), parseInt(body.get("age"))));

        return ResponseEntity.status(HttpStatus.OK).body(savedPatient);
    }

    public ResponseEntity<PatientLastNameResponse> getPatientWithoutLastName(Integer id){
        Optional<Patients> patientFromDb = repository.findById(id);
        HttpStatus httpStatus = HttpStatus.IM_USED;
        if (patientFromDb == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus)
                .body(new PatientLastNameResponse(patientFromDb.get()));
    }

    public ResponseEntity<PatientsListResponse> getPatientsBetweenAge(Integer age, Integer age2){

        HttpStatus httpStatus = HttpStatus.OK;

        PatientsListResponse patientsListResponse = filterByAge2(age, age2);

        if (patientsListResponse.getPatientsList().isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus)
                .body(patientsListResponse);
    }

    public ResponseEntity<Patients> updatePatient(Patients patientsRequest, Integer id) {
        Optional<Patients> patientsO = repository.findById(id);
        if (!patientsO.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Patients patientToUpdate = patientsO.get();

            patientToUpdate.setAge(patientsRequest.getAge());
            patientToUpdate.setFirstName(patientsRequest.getFirstName());
            patientToUpdate.setLastName((patientsRequest.getLastName()));

            repository.save(patientsO.get());
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<GetPatientsByCharacterResponse> getByCharacter(String ch) {
        List<Patients> patientsFromDb2 = repository.findAll();

        List<Patients> filteredList2 = patientsFromDb2.stream().filter(patients -> patients.getFirstName().startsWith(ch)).collect(Collectors.toList());

        HttpStatus httpStatus = HttpStatus.IM_USED;

        if (filteredList2.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus).body(new GetPatientsByCharacterResponse(filteredList2));


    }

    public ResponseEntity<PatientFirstNameOnlyResponse> getByFirstNameOnly(String firstName){
        HttpStatus httpStatus = HttpStatus.IM_USED;

        Patients firstNamePatient = repository.findByFirstName(firstName);

        if(firstNamePatient == null){
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus).body(new PatientFirstNameOnlyResponse(firstNamePatient));
    }

}





